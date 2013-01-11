/**   
* @Title: 		MappingInfo.java 
* @Package 		com.anthony.playstation.data.mapping 
* @Description:  
* 				Contains the class MappingInfo
* @author 		Anthony Fan
* @date 		2013-1-10 
* @time 		21:13:58 
* @version 		V 1.0   
*/
package com.anthony.playstation.data.mapping;

/**
 * A representation of mapping information within TSDB.
 */
public class MappingInfo {
	    private MappingType mapping=MappingType.MappingBaseObject;
		private int tsType=0;
		private String objectId="";
		private byte classificationMethod=0; 
		private byte regionId=0;
		private byte peerGroupId=0;
		private byte aggregationMethod=0;
		private String classificationId="0";
		private String taxRegionId="0";
		private String salesPositionId="0";
		private boolean originalReported=true;
		
		/**
		 * Method getTsType.
		 * @return int
		 */
		public int getTsType() {
			return tsType;
		}
		/**
		 * Method setTsType.
		 * @param tsType int
		 */
		public void setTsType(int tsType) {
			this.tsType = tsType;
		}
		/**
		 * Method getObjectId.
		 * @return String
		 */
		public String getObjectId() {
			return objectId;
		}
		/**
		 * Method setObjectId.
		 * @param objectId String
		 */
		public void setObjectId(String objectId) {
			this.objectId = objectId;
		}
		/**
		 * Method getClassificationMethod.
		 * @return byte
		 */
		public byte getClassificationMethod() {
			return classificationMethod;
		}
		/**
		 * Method setClassificationMethod.
		 * @param classificationMethod byte
		 */
		public void setClassificationMethod(byte classificationMethod) {
			this.classificationMethod = classificationMethod;
		}
		/**
		 * Method getRegionId.
		 * @return byte
		 */
		public byte getRegionId() {
			return regionId;
		}
		/**
		 * Method setRegionId.
		 * @param regionId byte
		 */
		public void setRegionId(byte regionId) {
			this.regionId = regionId;
		}
		/**
		 * Method getPeerGroupId.
		 * @return byte
		 */
		public byte getPeerGroupId() {
			return peerGroupId;
		}
		/**
		 * Method setPeerGroupId.
		 * @param peerGroupId byte
		 */
		public void setPeerGroupId(byte peerGroupId) {
			this.peerGroupId = peerGroupId;
		}
		/**
		 * Method getAggregationMethod.
		 * @return byte
		 */
		public byte getAggregationMethod() {
			return aggregationMethod;
		}
		/**
		 * Method setAggregationMethod.
		 * @param aggregationMethod byte
		 */
		public void setAggregationMethod(byte aggregationMethod) {
			this.aggregationMethod = aggregationMethod;
		}
		/**
		 * Method getClassificationId.
		 * @return String
		 */
		public String getClassificationId() {
			return classificationId;
		}
		/**
		 * Method setClassificationId.
		 * @param classificationId String
		 */
		public void setClassificationId(String classificationId) {
			this.classificationId = classificationId;
		}
		/**
		 * Method getTaxRegionId.
		 * @return String
		 */
		public String getTaxRegionId() {
			return taxRegionId;
		}
		/**
		 * Method setTaxRegionId.
		 * @param taxRegionId String
		 */
		public void setTaxRegionId(String taxRegionId) {
			this.taxRegionId = taxRegionId;
		}
		/**
		 * Method getSalesPositionId.
		 * @return String
		 */
		public String getSalesPositionId() {
			return salesPositionId;
		}
		/**
		 * Method setSalesPositionId.
		 * @param salesPositionId String
		 */
		public void setSalesPositionId(String salesPositionId) {
			this.salesPositionId = salesPositionId;
		}
		/**
		 * Method isOriginalReported.
		 * @return boolean
		 */
		public boolean isOriginalReported() {
			return originalReported;
		}
		/**
		 * Method setOriginalReported.
		 * @param originalReported boolean
		 */
		public void setOriginalReported(boolean originalReported) {
			this.originalReported = originalReported;
		}
		/**
		 * Constructor for MappingInfo.
		 * @param tsType int
		 * @param objectId String
		 * @param classificationMethod byte
		 * @param regionId byte
		 * @param peerGroupId byte
		 * @param aggregationMethod byte
		 * @param classificationId String
		 * @param taxRegionId String
		 * @param salesPositionId String
		 * @param originalReported boolean
		 */
		public MappingInfo(int tsType, String objectId, byte classificationMethod, 
	            byte regionId, byte peerGroupId, byte aggregationMethod,
	            String classificationId,String taxRegionId, String salesPositionId
	            , boolean originalReported)
	            {
			this.tsType=tsType;
			this.objectId=objectId;
			this.classificationMethod=classificationMethod; 
			this.regionId=regionId;
			this.peerGroupId=peerGroupId;
			this.aggregationMethod=aggregationMethod;
			this.classificationId=classificationId;
			this.taxRegionId=taxRegionId;
			this.salesPositionId=salesPositionId;
			this.originalReported=originalReported;
	            }
		public MappingInfo() {
		}
		
		/**
		 * Method toString.
		 * @return String
		 */
		public String toString(){
			StringBuilder sb = new StringBuilder();
			
			//tsType
			sb.append("tsType:"+tsType);
			//objectId
			sb.append(";objectId:"+objectId);
			//classificationMethod
			sb.append(";classificationMethod:"+classificationMethod);
			//regionId
			sb.append(";regionId:"+regionId);
			//peerGroupId
			sb.append(";peerGroupId:"+peerGroupId);
			//aggregationMethod
			sb.append(";aggregationMethod:"+aggregationMethod);
			//classificationId
			sb.append(";classificationId:"+getClassificationId());
			//taxRegionId
			sb.append(";taxRegionId:"+taxRegionId);
			//salesPositionId
			sb.append(";salesPositionId:"+salesPositionId);
			//originalReported
			sb.append(";originalReported:"+originalReported);
			
			return sb.toString();
		}
		
		/**
		 * Method getMapping.
		 * @return MappingType
		 */
		public MappingType getMapping() {
			return mapping;
		}
		/**
		 * Method setMapping.
		 * @param mapping MappingType
		 */
		public void setMapping(MappingType mapping) {
			this.mapping = mapping;
		}
	}