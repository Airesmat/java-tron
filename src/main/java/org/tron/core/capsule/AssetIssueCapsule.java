/*
 * java-tron is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * java-tron is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.tron.core.capsule;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.tron.protos.Contract.AssetIssueContract;
import org.tron.protos.Contract.AssetIssueContract.FrozenSupply;

@Slf4j
public class AssetIssueCapsule implements ProtoCapsule<AssetIssueContract> {

  private AssetIssueContract assetIssueContract;

  /**
   * get asset issue contract from bytes data.
   */
  public AssetIssueCapsule(byte[] data) {
    try {
      this.assetIssueContract = AssetIssueContract.parseFrom(data);
    } catch (InvalidProtocolBufferException e) {
      logger.debug(e.getMessage());
    }
  }

  public AssetIssueCapsule(AssetIssueContract assetIssueContract) {
    this.assetIssueContract = assetIssueContract;
  }

  public byte[] getData() {
    return this.assetIssueContract.toByteArray();
  }

  @Override
  public AssetIssueContract getInstance() {
    return this.assetIssueContract;
  }

  @Override
  public String toString() {
    return this.assetIssueContract.toString();
  }

  public ByteString getName() {
    return this.assetIssueContract.getName();
  }

  public byte[] createDbKey() {
    return getName().toByteArray();
  }

  public int getNum() {
    return this.assetIssueContract.getNum();
  }

  public int getTrxNum() {
    return this.assetIssueContract.getTrxNum();
  }

  public long getStartTime() {
    return this.assetIssueContract.getStartTime();
  }

  public long getEndTime() {
    return this.assetIssueContract.getEndTime();
  }

  public ByteString getOwnerAddress() {
    return this.assetIssueContract.getOwnerAddress();
  }

  public int getFrozenSupplyCount() {
    return getInstance().getFrozenSupplyCount();
  }

  public List<FrozenSupply> getFrozenSupplyList() {
    return getInstance().getFrozenSupplyList();
  }

  public long getFrozenSupply() {
    List<FrozenSupply> frozenList = getFrozenSupplyList();
    final long[] frozenBalance = {0};
    frozenList.forEach(frozen -> frozenBalance[0] = Long.sum(frozenBalance[0],
        frozen.getFrozenAmount()));
    return frozenBalance[0];
  }

  public long getFreeAssetNetLimit() {
    return this.assetIssueContract.getFreeAssetNetLimit();
  }

  public void setFreeAssetNetLimit(long newLimit) {
    this.assetIssueContract = this.assetIssueContract.toBuilder()
        .setFreeAssetNetLimit(newLimit).build();
  }

  public long getPublicFreeAssetNetLimit() {
    return this.assetIssueContract.getPublicFreeAssetNetLimit();
  }

  public void setPublicFreeAssetNetLimit(long newPublicLimit) {
    this.assetIssueContract = this.assetIssueContract.toBuilder()
        .setPublicFreeAssetNetLimit(newPublicLimit).build();
  }

  public long getPublicFreeAssetNetUsage() {
    return this.assetIssueContract.getPublicFreeAssetNetUsage();
  }

  public void setPublicFreeAssetNetUsage(long value) {
    this.assetIssueContract.toBuilder().setPublicFreeAssetNetUsage(value).build();
  }

  public long getPublicLatestFreeNetTime() {
    return this.assetIssueContract.getPublicLatestFreeNetTime();
  }

  public void setPublicLatestFreeNetTime(long time) {
    this.assetIssueContract.toBuilder().setPublicLatestFreeNetTime(time).build();
  }

  public void setUrl(ByteString newUrl) {
    this.assetIssueContract.toBuilder().setUrl(newUrl).build();
  }

  public void setDescription(ByteString string) {
    this.assetIssueContract.toBuilder().setDescription(string).build();
  }
}
