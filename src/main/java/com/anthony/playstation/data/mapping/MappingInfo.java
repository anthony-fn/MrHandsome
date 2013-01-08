package com.anthony.playstation.data.mapping;

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
		
		public int getTsType() {
			return tsType;
		}
		public void setTsType(int tsType) {
			this.tsType = tsType;
		}
		public String getObjectId() {
			return objectId;
		}
		public void setObjectId(String objectId) {
			this.objectId = objectId;
		}
		public byte getClassificationMethod() {
			return classificationMethod;
		}
		public void setClassificationMethod(byte classificationMethod) {
			this.classificationMethod = classificationMethod;
		}
		public byte getRegionId() {
			return regionId;
		}
		public void setRegionId(byte regionId) {
			this.regionId = regionId;
		}
		public byte getPeerGroupId() {
			return peerGroupId;
		}
		public void setPeerGroupId(byte peerGroupId) {
			this.peerGroupId = peerGroupId;
		}
		public byte getAggregationMethod() {
			return aggregationMethod;
		}
		public void setAggregationMethod(byte aggregationMethod) {
			this.aggregationMethod = aggregationMethod;
		}
		public String getClassificationId() {
			return classificationId;
		}
		public void setClassificationId(String classificationId) {
			this.classificationId = classificationId;
		}
		public String getTaxRegionId() {
			return taxRegionId;
		}
		public void setTaxRegionId(String taxRegionId) {
			this.taxRegionId = taxRegionId;
		}
		public String getSalesPositionId() {
			return salesPositionId;
		}
		public void setSalesPositionId(String salesPositionId) {
			this.salesPositionId = salesPositionId;
		}
		public boolean isOriginalReported() {
			return originalReported;
		}
		public void setOriginalReported(boolean originalReported) {
			this.originalReported = originalReported;
		}
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
		
		public String toString(){
			StringBuilder sb = new StringBuilder();
			
			//tsType
			sb.append(";tsType:"+tsType);
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
		
		public MappingType getMapping() {
			return mapping;
		}
		public void setMapping(MappingType mapping) {
			this.mapping = mapping;
		}
	}