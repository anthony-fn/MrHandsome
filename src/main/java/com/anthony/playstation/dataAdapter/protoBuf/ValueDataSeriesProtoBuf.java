package com.anthony.playstation.dataAdapter.protoBuf;


public final class ValueDataSeriesProtoBuf {
  private ValueDataSeriesProtoBuf() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface ValueDataSeriesForProtoOrBuilder
      extends com.google.protobuf.MessageOrBuilder {
    
    // required string id = 1;
    boolean hasId();
    String getId();
    
    // required .ValueDataSeriesForProto.UniformTypeForProto uniformType = 2;
    boolean hasUniformType();
    ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto getUniformType();
    ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProtoOrBuilder getUniformTypeOrBuilder();
    
    // repeated .ValueDataSeriesForProto.ValueUnitForProto valueList = 3;
    java.util.List<ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto> 
        getValueListList();
    ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto getValueList(int index);
    int getValueListCount();
    java.util.List<? extends ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProtoOrBuilder> 
        getValueListOrBuilderList();
    ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProtoOrBuilder getValueListOrBuilder(
        int index);
  }
  public static final class ValueDataSeriesForProto extends
      com.google.protobuf.GeneratedMessage
      implements ValueDataSeriesForProtoOrBuilder {
    // Use ValueDataSeriesForProto.newBuilder() to construct.
    private ValueDataSeriesForProto(Builder builder) {
      super(builder);
    }
    private ValueDataSeriesForProto(boolean noInit) {}
    
    private static final ValueDataSeriesForProto defaultInstance;
    public static ValueDataSeriesForProto getDefaultInstance() {
      return defaultInstance;
    }
    
    public ValueDataSeriesForProto getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return ValueDataSeriesProtoBuf.internal_static_ValueDataSeriesForProto_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return ValueDataSeriesProtoBuf.internal_static_ValueDataSeriesForProto_fieldAccessorTable;
    }
    
    public enum DataUnitTypeForProto
        implements com.google.protobuf.ProtocolMessageEnum {
      DEFAULT(0, 0),
      VALUE(1, 1),
      STRING(2, 2),
      ;
      
      public static final int DEFAULT_VALUE = 0;
      public static final int VALUE_VALUE = 1;
      public static final int STRING_VALUE = 2;
      
      
      public final int getNumber() { return value; }
      
      public static DataUnitTypeForProto valueOf(int value) {
        switch (value) {
          case 0: return DEFAULT;
          case 1: return VALUE;
          case 2: return STRING;
          default: return null;
        }
      }
      
      public static com.google.protobuf.Internal.EnumLiteMap<DataUnitTypeForProto>
          internalGetValueMap() {
        return internalValueMap;
      }
      private static com.google.protobuf.Internal.EnumLiteMap<DataUnitTypeForProto>
          internalValueMap =
            new com.google.protobuf.Internal.EnumLiteMap<DataUnitTypeForProto>() {
              public DataUnitTypeForProto findValueByNumber(int number) {
                return DataUnitTypeForProto.valueOf(number);
              }
            };
      
      public final com.google.protobuf.Descriptors.EnumValueDescriptor
          getValueDescriptor() {
        return getDescriptor().getValues().get(index);
      }
      public final com.google.protobuf.Descriptors.EnumDescriptor
          getDescriptorForType() {
        return getDescriptor();
      }
      public static final com.google.protobuf.Descriptors.EnumDescriptor
          getDescriptor() {
        return ValueDataSeriesProtoBuf.ValueDataSeriesForProto.getDescriptor().getEnumTypes().get(0);
      }
      
      private static final DataUnitTypeForProto[] VALUES = {
        DEFAULT, VALUE, STRING, 
      };
      
      public static DataUnitTypeForProto valueOf(
          com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
        if (desc.getType() != getDescriptor()) {
          throw new java.lang.IllegalArgumentException(
            "EnumValueDescriptor is not for this type.");
        }
        return VALUES[desc.getIndex()];
      }
      
      private final int index;
      private final int value;
      
      private DataUnitTypeForProto(int index, int value) {
        this.index = index;
        this.value = value;
      }
      
      // @@protoc_insertion_point(enum_scope:ValueDataSeriesForProto.DataUnitTypeForProto)
    }
    
    public interface UniformTypeForProtoOrBuilder
        extends com.google.protobuf.MessageOrBuilder {
      
      // required int32 typeID = 1;
      boolean hasTypeID();
      int getTypeID();
      
      // required string typeName = 2;
      boolean hasTypeName();
      String getTypeName();
      
      // required .ValueDataSeriesForProto.DataUnitTypeForProto unitType = 3 [default = DEFAULT];
      boolean hasUnitType();
      ValueDataSeriesProtoBuf.ValueDataSeriesForProto.DataUnitTypeForProto getUnitType();
    }
    public static final class UniformTypeForProto extends
        com.google.protobuf.GeneratedMessage
        implements UniformTypeForProtoOrBuilder {
      // Use UniformTypeForProto.newBuilder() to construct.
      private UniformTypeForProto(Builder builder) {
        super(builder);
      }
      private UniformTypeForProto(boolean noInit) {}
      
      private static final UniformTypeForProto defaultInstance;
      public static UniformTypeForProto getDefaultInstance() {
        return defaultInstance;
      }
      
      public UniformTypeForProto getDefaultInstanceForType() {
        return defaultInstance;
      }
      
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return ValueDataSeriesProtoBuf.internal_static_ValueDataSeriesForProto_UniformTypeForProto_descriptor;
      }
      
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return ValueDataSeriesProtoBuf.internal_static_ValueDataSeriesForProto_UniformTypeForProto_fieldAccessorTable;
      }
      
      private int bitField0_;
      // required int32 typeID = 1;
      public static final int TYPEID_FIELD_NUMBER = 1;
      private int typeID_;
      public boolean hasTypeID() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      public int getTypeID() {
        return typeID_;
      }
      
      // required string typeName = 2;
      public static final int TYPENAME_FIELD_NUMBER = 2;
      private java.lang.Object typeName_;
      public boolean hasTypeName() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      public String getTypeName() {
        java.lang.Object ref = typeName_;
        if (ref instanceof String) {
          return (String) ref;
        } else {
          com.google.protobuf.ByteString bs = 
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          if (com.google.protobuf.Internal.isValidUtf8(bs)) {
            typeName_ = s;
          }
          return s;
        }
      }
      private com.google.protobuf.ByteString getTypeNameBytes() {
        java.lang.Object ref = typeName_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8((String) ref);
          typeName_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      
      // required .ValueDataSeriesForProto.DataUnitTypeForProto unitType = 3 [default = DEFAULT];
      public static final int UNITTYPE_FIELD_NUMBER = 3;
      private ValueDataSeriesProtoBuf.ValueDataSeriesForProto.DataUnitTypeForProto unitType_;
      public boolean hasUnitType() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      public ValueDataSeriesProtoBuf.ValueDataSeriesForProto.DataUnitTypeForProto getUnitType() {
        return unitType_;
      }
      
      private void initFields() {
        typeID_ = 0;
        typeName_ = "";
        unitType_ = ValueDataSeriesProtoBuf.ValueDataSeriesForProto.DataUnitTypeForProto.DEFAULT;
      }
      private byte memoizedIsInitialized = -1;
      public final boolean isInitialized() {
        byte isInitialized = memoizedIsInitialized;
        if (isInitialized != -1) return isInitialized == 1;
        
        if (!hasTypeID()) {
          memoizedIsInitialized = 0;
          return false;
        }
        if (!hasTypeName()) {
          memoizedIsInitialized = 0;
          return false;
        }
        if (!hasUnitType()) {
          memoizedIsInitialized = 0;
          return false;
        }
        memoizedIsInitialized = 1;
        return true;
      }
      
      public void writeTo(com.google.protobuf.CodedOutputStream output)
                          throws java.io.IOException {
        getSerializedSize();
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          output.writeInt32(1, typeID_);
        }
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          output.writeBytes(2, getTypeNameBytes());
        }
        if (((bitField0_ & 0x00000004) == 0x00000004)) {
          output.writeEnum(3, unitType_.getNumber());
        }
        getUnknownFields().writeTo(output);
      }
      
      private int memoizedSerializedSize = -1;
      public int getSerializedSize() {
        int size = memoizedSerializedSize;
        if (size != -1) return size;
      
        size = 0;
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          size += com.google.protobuf.CodedOutputStream
            .computeInt32Size(1, typeID_);
        }
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          size += com.google.protobuf.CodedOutputStream
            .computeBytesSize(2, getTypeNameBytes());
        }
        if (((bitField0_ & 0x00000004) == 0x00000004)) {
          size += com.google.protobuf.CodedOutputStream
            .computeEnumSize(3, unitType_.getNumber());
        }
        size += getUnknownFields().getSerializedSize();
        memoizedSerializedSize = size;
        return size;
      }
      
      private static final long serialVersionUID = 0L;
      @java.lang.Override
      protected java.lang.Object writeReplace()
          throws java.io.ObjectStreamException {
        return super.writeReplace();
      }
      
      public static ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto parseFrom(
          com.google.protobuf.ByteString data)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return newBuilder().mergeFrom(data).buildParsed();
      }
      public static ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto parseFrom(
          com.google.protobuf.ByteString data,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return newBuilder().mergeFrom(data, extensionRegistry)
                 .buildParsed();
      }
      public static ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto parseFrom(byte[] data)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return newBuilder().mergeFrom(data).buildParsed();
      }
      public static ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto parseFrom(
          byte[] data,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return newBuilder().mergeFrom(data, extensionRegistry)
                 .buildParsed();
      }
      public static ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto parseFrom(java.io.InputStream input)
          throws java.io.IOException {
        return newBuilder().mergeFrom(input).buildParsed();
      }
      public static ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto parseFrom(
          java.io.InputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        return newBuilder().mergeFrom(input, extensionRegistry)
                 .buildParsed();
      }
      public static ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto parseDelimitedFrom(java.io.InputStream input)
          throws java.io.IOException {
        Builder builder = newBuilder();
        if (builder.mergeDelimitedFrom(input)) {
          return builder.buildParsed();
        } else {
          return null;
        }
      }
      public static ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto parseDelimitedFrom(
          java.io.InputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        Builder builder = newBuilder();
        if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
          return builder.buildParsed();
        } else {
          return null;
        }
      }
      public static ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto parseFrom(
          com.google.protobuf.CodedInputStream input)
          throws java.io.IOException {
        return newBuilder().mergeFrom(input).buildParsed();
      }
      public static ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto parseFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        return newBuilder().mergeFrom(input, extensionRegistry)
                 .buildParsed();
      }
      
      public static Builder newBuilder() { return Builder.create(); }
      public Builder newBuilderForType() { return newBuilder(); }
      public static Builder newBuilder(ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto prototype) {
        return newBuilder().mergeFrom(prototype);
      }
      public Builder toBuilder() { return newBuilder(this); }
      
      @java.lang.Override
      protected Builder newBuilderForType(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        Builder builder = new Builder(parent);
        return builder;
      }
      public static final class Builder extends
          com.google.protobuf.GeneratedMessage.Builder<Builder>
         implements ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProtoOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
          return ValueDataSeriesProtoBuf.internal_static_ValueDataSeriesForProto_UniformTypeForProto_descriptor;
        }
        
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
            internalGetFieldAccessorTable() {
          return ValueDataSeriesProtoBuf.internal_static_ValueDataSeriesForProto_UniformTypeForProto_fieldAccessorTable;
        }
        
        // Construct using ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto.newBuilder()
        private Builder() {
          maybeForceBuilderInitialization();
        }
        
        private Builder(BuilderParent parent) {
          super(parent);
          maybeForceBuilderInitialization();
        }
        private void maybeForceBuilderInitialization() {
          if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
          }
        }
        private static Builder create() {
          return new Builder();
        }
        
        public Builder clear() {
          super.clear();
          typeID_ = 0;
          bitField0_ = (bitField0_ & ~0x00000001);
          typeName_ = "";
          bitField0_ = (bitField0_ & ~0x00000002);
          unitType_ = ValueDataSeriesProtoBuf.ValueDataSeriesForProto.DataUnitTypeForProto.DEFAULT;
          bitField0_ = (bitField0_ & ~0x00000004);
          return this;
        }
        
        public Builder clone() {
          return create().mergeFrom(buildPartial());
        }
        
        public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
          return ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto.getDescriptor();
        }
        
        public ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto getDefaultInstanceForType() {
          return ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto.getDefaultInstance();
        }
        
        public ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto build() {
          ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto result = buildPartial();
          if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
          }
          return result;
        }
        
        private ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto buildParsed()
            throws com.google.protobuf.InvalidProtocolBufferException {
          ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto result = buildPartial();
          if (!result.isInitialized()) {
            throw newUninitializedMessageException(
              result).asInvalidProtocolBufferException();
          }
          return result;
        }
        
        public ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto buildPartial() {
          ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto result = new ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto(this);
          int from_bitField0_ = bitField0_;
          int to_bitField0_ = 0;
          if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
            to_bitField0_ |= 0x00000001;
          }
          result.typeID_ = typeID_;
          if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
            to_bitField0_ |= 0x00000002;
          }
          result.typeName_ = typeName_;
          if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
            to_bitField0_ |= 0x00000004;
          }
          result.unitType_ = unitType_;
          result.bitField0_ = to_bitField0_;
          onBuilt();
          return result;
        }
        
        public Builder mergeFrom(com.google.protobuf.Message other) {
          if (other instanceof ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto) {
            return mergeFrom((ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto)other);
          } else {
            super.mergeFrom(other);
            return this;
          }
        }
        
        public Builder mergeFrom(ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto other) {
          if (other == ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto.getDefaultInstance()) return this;
          if (other.hasTypeID()) {
            setTypeID(other.getTypeID());
          }
          if (other.hasTypeName()) {
            setTypeName(other.getTypeName());
          }
          if (other.hasUnitType()) {
            setUnitType(other.getUnitType());
          }
          this.mergeUnknownFields(other.getUnknownFields());
          return this;
        }
        
        public final boolean isInitialized() {
          if (!hasTypeID()) {
            
            return false;
          }
          if (!hasTypeName()) {
            
            return false;
          }
          if (!hasUnitType()) {
            
            return false;
          }
          return true;
        }
        
        public Builder mergeFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
          com.google.protobuf.UnknownFieldSet.Builder unknownFields =
            com.google.protobuf.UnknownFieldSet.newBuilder(
              this.getUnknownFields());
          while (true) {
            int tag = input.readTag();
            switch (tag) {
              case 0:
                this.setUnknownFields(unknownFields.build());
                onChanged();
                return this;
              default: {
                if (!parseUnknownField(input, unknownFields,
                                       extensionRegistry, tag)) {
                  this.setUnknownFields(unknownFields.build());
                  onChanged();
                  return this;
                }
                break;
              }
              case 8: {
                bitField0_ |= 0x00000001;
                typeID_ = input.readInt32();
                break;
              }
              case 18: {
                bitField0_ |= 0x00000002;
                typeName_ = input.readBytes();
                break;
              }
              case 24: {
                int rawValue = input.readEnum();
                ValueDataSeriesProtoBuf.ValueDataSeriesForProto.DataUnitTypeForProto value = ValueDataSeriesProtoBuf.ValueDataSeriesForProto.DataUnitTypeForProto.valueOf(rawValue);
                if (value == null) {
                  unknownFields.mergeVarintField(3, rawValue);
                } else {
                  bitField0_ |= 0x00000004;
                  unitType_ = value;
                }
                break;
              }
            }
          }
        }
        
        private int bitField0_;
        
        // required int32 typeID = 1;
        private int typeID_ ;
        public boolean hasTypeID() {
          return ((bitField0_ & 0x00000001) == 0x00000001);
        }
        public int getTypeID() {
          return typeID_;
        }
        public Builder setTypeID(int value) {
          bitField0_ |= 0x00000001;
          typeID_ = value;
          onChanged();
          return this;
        }
        public Builder clearTypeID() {
          bitField0_ = (bitField0_ & ~0x00000001);
          typeID_ = 0;
          onChanged();
          return this;
        }
        
        // required string typeName = 2;
        private java.lang.Object typeName_ = "";
        public boolean hasTypeName() {
          return ((bitField0_ & 0x00000002) == 0x00000002);
        }
        public String getTypeName() {
          java.lang.Object ref = typeName_;
          if (!(ref instanceof String)) {
            String s = ((com.google.protobuf.ByteString) ref).toStringUtf8();
            typeName_ = s;
            return s;
          } else {
            return (String) ref;
          }
        }
        public Builder setTypeName(String value) {
          if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
          typeName_ = value;
          onChanged();
          return this;
        }
        public Builder clearTypeName() {
          bitField0_ = (bitField0_ & ~0x00000002);
          typeName_ = getDefaultInstance().getTypeName();
          onChanged();
          return this;
        }
        void setTypeName(com.google.protobuf.ByteString value) {
          bitField0_ |= 0x00000002;
          typeName_ = value;
          onChanged();
        }
        
        // required .ValueDataSeriesForProto.DataUnitTypeForProto unitType = 3 [default = DEFAULT];
        private ValueDataSeriesProtoBuf.ValueDataSeriesForProto.DataUnitTypeForProto unitType_ = ValueDataSeriesProtoBuf.ValueDataSeriesForProto.DataUnitTypeForProto.DEFAULT;
        public boolean hasUnitType() {
          return ((bitField0_ & 0x00000004) == 0x00000004);
        }
        public ValueDataSeriesProtoBuf.ValueDataSeriesForProto.DataUnitTypeForProto getUnitType() {
          return unitType_;
        }
        public Builder setUnitType(ValueDataSeriesProtoBuf.ValueDataSeriesForProto.DataUnitTypeForProto value) {
          if (value == null) {
            throw new NullPointerException();
          }
          bitField0_ |= 0x00000004;
          unitType_ = value;
          onChanged();
          return this;
        }
        public Builder clearUnitType() {
          bitField0_ = (bitField0_ & ~0x00000004);
          unitType_ = ValueDataSeriesProtoBuf.ValueDataSeriesForProto.DataUnitTypeForProto.DEFAULT;
          onChanged();
          return this;
        }
        
        // @@protoc_insertion_point(builder_scope:ValueDataSeriesForProto.UniformTypeForProto)
      }
      
      static {
        defaultInstance = new UniformTypeForProto(true);
        defaultInstance.initFields();
      }
      
      // @@protoc_insertion_point(class_scope:ValueDataSeriesForProto.UniformTypeForProto)
    }
    
    public interface ValueUnitForProtoOrBuilder
        extends com.google.protobuf.MessageOrBuilder {
      
      // required string date = 1;
      boolean hasDate();
      String getDate();
      
      // required float value = 2;
      boolean hasValue();
      float getValue();
    }
    public static final class ValueUnitForProto extends
        com.google.protobuf.GeneratedMessage
        implements ValueUnitForProtoOrBuilder {
      // Use ValueUnitForProto.newBuilder() to construct.
      private ValueUnitForProto(Builder builder) {
        super(builder);
      }
      private ValueUnitForProto(boolean noInit) {}
      
      private static final ValueUnitForProto defaultInstance;
      public static ValueUnitForProto getDefaultInstance() {
        return defaultInstance;
      }
      
      public ValueUnitForProto getDefaultInstanceForType() {
        return defaultInstance;
      }
      
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return ValueDataSeriesProtoBuf.internal_static_ValueDataSeriesForProto_ValueUnitForProto_descriptor;
      }
      
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return ValueDataSeriesProtoBuf.internal_static_ValueDataSeriesForProto_ValueUnitForProto_fieldAccessorTable;
      }
      
      private int bitField0_;
      // required string date = 1;
      public static final int DATE_FIELD_NUMBER = 1;
      private java.lang.Object date_;
      public boolean hasDate() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      public String getDate() {
        java.lang.Object ref = date_;
        if (ref instanceof String) {
          return (String) ref;
        } else {
          com.google.protobuf.ByteString bs = 
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          if (com.google.protobuf.Internal.isValidUtf8(bs)) {
            date_ = s;
          }
          return s;
        }
      }
      private com.google.protobuf.ByteString getDateBytes() {
        java.lang.Object ref = date_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8((String) ref);
          date_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      
      // required float value = 2;
      public static final int VALUE_FIELD_NUMBER = 2;
      private float value_;
      public boolean hasValue() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      public float getValue() {
        return value_;
      }
      
      private void initFields() {
        date_ = "";
        value_ = 0F;
      }
      private byte memoizedIsInitialized = -1;
      public final boolean isInitialized() {
        byte isInitialized = memoizedIsInitialized;
        if (isInitialized != -1) return isInitialized == 1;
        
        if (!hasDate()) {
          memoizedIsInitialized = 0;
          return false;
        }
        if (!hasValue()) {
          memoizedIsInitialized = 0;
          return false;
        }
        memoizedIsInitialized = 1;
        return true;
      }
      
      public void writeTo(com.google.protobuf.CodedOutputStream output)
                          throws java.io.IOException {
        getSerializedSize();
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          output.writeBytes(1, getDateBytes());
        }
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          output.writeFloat(2, value_);
        }
        getUnknownFields().writeTo(output);
      }
      
      private int memoizedSerializedSize = -1;
      public int getSerializedSize() {
        int size = memoizedSerializedSize;
        if (size != -1) return size;
      
        size = 0;
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          size += com.google.protobuf.CodedOutputStream
            .computeBytesSize(1, getDateBytes());
        }
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          size += com.google.protobuf.CodedOutputStream
            .computeFloatSize(2, value_);
        }
        size += getUnknownFields().getSerializedSize();
        memoizedSerializedSize = size;
        return size;
      }
      
      private static final long serialVersionUID = 0L;
      @java.lang.Override
      protected java.lang.Object writeReplace()
          throws java.io.ObjectStreamException {
        return super.writeReplace();
      }
      
      public static ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto parseFrom(
          com.google.protobuf.ByteString data)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return newBuilder().mergeFrom(data).buildParsed();
      }
      public static ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto parseFrom(
          com.google.protobuf.ByteString data,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return newBuilder().mergeFrom(data, extensionRegistry)
                 .buildParsed();
      }
      public static ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto parseFrom(byte[] data)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return newBuilder().mergeFrom(data).buildParsed();
      }
      public static ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto parseFrom(
          byte[] data,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return newBuilder().mergeFrom(data, extensionRegistry)
                 .buildParsed();
      }
      public static ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto parseFrom(java.io.InputStream input)
          throws java.io.IOException {
        return newBuilder().mergeFrom(input).buildParsed();
      }
      public static ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto parseFrom(
          java.io.InputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        return newBuilder().mergeFrom(input, extensionRegistry)
                 .buildParsed();
      }
      public static ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto parseDelimitedFrom(java.io.InputStream input)
          throws java.io.IOException {
        Builder builder = newBuilder();
        if (builder.mergeDelimitedFrom(input)) {
          return builder.buildParsed();
        } else {
          return null;
        }
      }
      public static ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto parseDelimitedFrom(
          java.io.InputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        Builder builder = newBuilder();
        if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
          return builder.buildParsed();
        } else {
          return null;
        }
      }
      public static ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto parseFrom(
          com.google.protobuf.CodedInputStream input)
          throws java.io.IOException {
        return newBuilder().mergeFrom(input).buildParsed();
      }
      public static ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto parseFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        return newBuilder().mergeFrom(input, extensionRegistry)
                 .buildParsed();
      }
      
      public static Builder newBuilder() { return Builder.create(); }
      public Builder newBuilderForType() { return newBuilder(); }
      public static Builder newBuilder(ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto prototype) {
        return newBuilder().mergeFrom(prototype);
      }
      public Builder toBuilder() { return newBuilder(this); }
      
      @java.lang.Override
      protected Builder newBuilderForType(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        Builder builder = new Builder(parent);
        return builder;
      }
      public static final class Builder extends
          com.google.protobuf.GeneratedMessage.Builder<Builder>
         implements ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProtoOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
          return ValueDataSeriesProtoBuf.internal_static_ValueDataSeriesForProto_ValueUnitForProto_descriptor;
        }
        
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
            internalGetFieldAccessorTable() {
          return ValueDataSeriesProtoBuf.internal_static_ValueDataSeriesForProto_ValueUnitForProto_fieldAccessorTable;
        }
        
        // Construct using ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto.newBuilder()
        private Builder() {
          maybeForceBuilderInitialization();
        }
        
        private Builder(BuilderParent parent) {
          super(parent);
          maybeForceBuilderInitialization();
        }
        private void maybeForceBuilderInitialization() {
          if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
          }
        }
        private static Builder create() {
          return new Builder();
        }
        
        public Builder clear() {
          super.clear();
          date_ = "";
          bitField0_ = (bitField0_ & ~0x00000001);
          value_ = 0F;
          bitField0_ = (bitField0_ & ~0x00000002);
          return this;
        }
        
        public Builder clone() {
          return create().mergeFrom(buildPartial());
        }
        
        public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
          return ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto.getDescriptor();
        }
        
        public ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto getDefaultInstanceForType() {
          return ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto.getDefaultInstance();
        }
        
        public ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto build() {
          ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto result = buildPartial();
          if (!result.isInitialized()) {
            throw newUninitializedMessageException(result);
          }
          return result;
        }
        
        private ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto buildParsed()
            throws com.google.protobuf.InvalidProtocolBufferException {
          ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto result = buildPartial();
          if (!result.isInitialized()) {
            throw newUninitializedMessageException(
              result).asInvalidProtocolBufferException();
          }
          return result;
        }
        
        public ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto buildPartial() {
          ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto result = new ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto(this);
          int from_bitField0_ = bitField0_;
          int to_bitField0_ = 0;
          if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
            to_bitField0_ |= 0x00000001;
          }
          result.date_ = date_;
          if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
            to_bitField0_ |= 0x00000002;
          }
          result.value_ = value_;
          result.bitField0_ = to_bitField0_;
          onBuilt();
          return result;
        }
        
        public Builder mergeFrom(com.google.protobuf.Message other) {
          if (other instanceof ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto) {
            return mergeFrom((ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto)other);
          } else {
            super.mergeFrom(other);
            return this;
          }
        }
        
        public Builder mergeFrom(ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto other) {
          if (other == ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto.getDefaultInstance()) return this;
          if (other.hasDate()) {
            setDate(other.getDate());
          }
          if (other.hasValue()) {
            setValue(other.getValue());
          }
          this.mergeUnknownFields(other.getUnknownFields());
          return this;
        }
        
        public final boolean isInitialized() {
          if (!hasDate()) {
            
            return false;
          }
          if (!hasValue()) {
            
            return false;
          }
          return true;
        }
        
        public Builder mergeFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
          com.google.protobuf.UnknownFieldSet.Builder unknownFields =
            com.google.protobuf.UnknownFieldSet.newBuilder(
              this.getUnknownFields());
          while (true) {
            int tag = input.readTag();
            switch (tag) {
              case 0:
                this.setUnknownFields(unknownFields.build());
                onChanged();
                return this;
              default: {
                if (!parseUnknownField(input, unknownFields,
                                       extensionRegistry, tag)) {
                  this.setUnknownFields(unknownFields.build());
                  onChanged();
                  return this;
                }
                break;
              }
              case 10: {
                bitField0_ |= 0x00000001;
                date_ = input.readBytes();
                break;
              }
              case 21: {
                bitField0_ |= 0x00000002;
                value_ = input.readFloat();
                break;
              }
            }
          }
        }
        
        private int bitField0_;
        
        // required string date = 1;
        private java.lang.Object date_ = "";
        public boolean hasDate() {
          return ((bitField0_ & 0x00000001) == 0x00000001);
        }
        public String getDate() {
          java.lang.Object ref = date_;
          if (!(ref instanceof String)) {
            String s = ((com.google.protobuf.ByteString) ref).toStringUtf8();
            date_ = s;
            return s;
          } else {
            return (String) ref;
          }
        }
        public Builder setDate(String value) {
          if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
          date_ = value;
          onChanged();
          return this;
        }
        public Builder clearDate() {
          bitField0_ = (bitField0_ & ~0x00000001);
          date_ = getDefaultInstance().getDate();
          onChanged();
          return this;
        }
        void setDate(com.google.protobuf.ByteString value) {
          bitField0_ |= 0x00000001;
          date_ = value;
          onChanged();
        }
        
        // required float value = 2;
        private float value_ ;
        public boolean hasValue() {
          return ((bitField0_ & 0x00000002) == 0x00000002);
        }
        public float getValue() {
          return value_;
        }
        public Builder setValue(float value) {
          bitField0_ |= 0x00000002;
          value_ = value;
          onChanged();
          return this;
        }
        public Builder clearValue() {
          bitField0_ = (bitField0_ & ~0x00000002);
          value_ = 0F;
          onChanged();
          return this;
        }
        
        // @@protoc_insertion_point(builder_scope:ValueDataSeriesForProto.ValueUnitForProto)
      }
      
      static {
        defaultInstance = new ValueUnitForProto(true);
        defaultInstance.initFields();
      }
      
      // @@protoc_insertion_point(class_scope:ValueDataSeriesForProto.ValueUnitForProto)
    }
    
    private int bitField0_;
    // required string id = 1;
    public static final int ID_FIELD_NUMBER = 1;
    private java.lang.Object id_;
    public boolean hasId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    public String getId() {
      java.lang.Object ref = id_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (com.google.protobuf.Internal.isValidUtf8(bs)) {
          id_ = s;
        }
        return s;
      }
    }
    private com.google.protobuf.ByteString getIdBytes() {
      java.lang.Object ref = id_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8((String) ref);
        id_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    
    // required .ValueDataSeriesForProto.UniformTypeForProto uniformType = 2;
    public static final int UNIFORMTYPE_FIELD_NUMBER = 2;
    private ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto uniformType_;
    public boolean hasUniformType() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    public ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto getUniformType() {
      return uniformType_;
    }
    public ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProtoOrBuilder getUniformTypeOrBuilder() {
      return uniformType_;
    }
    
    // repeated .ValueDataSeriesForProto.ValueUnitForProto valueList = 3;
    public static final int VALUELIST_FIELD_NUMBER = 3;
    private java.util.List<ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto> valueList_;
    public java.util.List<ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto> getValueListList() {
      return valueList_;
    }
    public java.util.List<? extends ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProtoOrBuilder> 
        getValueListOrBuilderList() {
      return valueList_;
    }
    public int getValueListCount() {
      return valueList_.size();
    }
    public ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto getValueList(int index) {
      return valueList_.get(index);
    }
    public ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProtoOrBuilder getValueListOrBuilder(
        int index) {
      return valueList_.get(index);
    }
    
    private void initFields() {
      id_ = "";
      uniformType_ = ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto.getDefaultInstance();
      valueList_ = java.util.Collections.emptyList();
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;
      
      if (!hasId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasUniformType()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!getUniformType().isInitialized()) {
        memoizedIsInitialized = 0;
        return false;
      }
      for (int i = 0; i < getValueListCount(); i++) {
        if (!getValueList(i).isInitialized()) {
          memoizedIsInitialized = 0;
          return false;
        }
      }
      memoizedIsInitialized = 1;
      return true;
    }
    
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeBytes(1, getIdBytes());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeMessage(2, uniformType_);
      }
      for (int i = 0; i < valueList_.size(); i++) {
        output.writeMessage(3, valueList_.get(i));
      }
      getUnknownFields().writeTo(output);
    }
    
    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;
    
      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(1, getIdBytes());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(2, uniformType_);
      }
      for (int i = 0; i < valueList_.size(); i++) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(3, valueList_.get(i));
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }
    
    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }
    
    public static ValueDataSeriesProtoBuf.ValueDataSeriesForProto parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static ValueDataSeriesProtoBuf.ValueDataSeriesForProto parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static ValueDataSeriesProtoBuf.ValueDataSeriesForProto parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static ValueDataSeriesProtoBuf.ValueDataSeriesForProto parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static ValueDataSeriesProtoBuf.ValueDataSeriesForProto parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static ValueDataSeriesProtoBuf.ValueDataSeriesForProto parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static ValueDataSeriesProtoBuf.ValueDataSeriesForProto parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static ValueDataSeriesProtoBuf.ValueDataSeriesForProto parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static ValueDataSeriesProtoBuf.ValueDataSeriesForProto parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static ValueDataSeriesProtoBuf.ValueDataSeriesForProto parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(ValueDataSeriesProtoBuf.ValueDataSeriesForProto prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }
    
    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements ValueDataSeriesProtoBuf.ValueDataSeriesForProtoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return ValueDataSeriesProtoBuf.internal_static_ValueDataSeriesForProto_descriptor;
      }
      
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return ValueDataSeriesProtoBuf.internal_static_ValueDataSeriesForProto_fieldAccessorTable;
      }
      
      // Construct using ValueDataSeriesProtoBuf.ValueDataSeriesForProto.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
          getUniformTypeFieldBuilder();
          getValueListFieldBuilder();
        }
      }
      private static Builder create() {
        return new Builder();
      }
      
      public Builder clear() {
        super.clear();
        id_ = "";
        bitField0_ = (bitField0_ & ~0x00000001);
        if (uniformTypeBuilder_ == null) {
          uniformType_ = ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto.getDefaultInstance();
        } else {
          uniformTypeBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000002);
        if (valueListBuilder_ == null) {
          valueList_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000004);
        } else {
          valueListBuilder_.clear();
        }
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return ValueDataSeriesProtoBuf.ValueDataSeriesForProto.getDescriptor();
      }
      
      public ValueDataSeriesProtoBuf.ValueDataSeriesForProto getDefaultInstanceForType() {
        return ValueDataSeriesProtoBuf.ValueDataSeriesForProto.getDefaultInstance();
      }
      
      public ValueDataSeriesProtoBuf.ValueDataSeriesForProto build() {
        ValueDataSeriesProtoBuf.ValueDataSeriesForProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }
      
      private ValueDataSeriesProtoBuf.ValueDataSeriesForProto buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        ValueDataSeriesProtoBuf.ValueDataSeriesForProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return result;
      }
      
      public ValueDataSeriesProtoBuf.ValueDataSeriesForProto buildPartial() {
        ValueDataSeriesProtoBuf.ValueDataSeriesForProto result = new ValueDataSeriesProtoBuf.ValueDataSeriesForProto(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.id_ = id_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        if (uniformTypeBuilder_ == null) {
          result.uniformType_ = uniformType_;
        } else {
          result.uniformType_ = uniformTypeBuilder_.build();
        }
        if (valueListBuilder_ == null) {
          if (((bitField0_ & 0x00000004) == 0x00000004)) {
            valueList_ = java.util.Collections.unmodifiableList(valueList_);
            bitField0_ = (bitField0_ & ~0x00000004);
          }
          result.valueList_ = valueList_;
        } else {
          result.valueList_ = valueListBuilder_.build();
        }
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof ValueDataSeriesProtoBuf.ValueDataSeriesForProto) {
          return mergeFrom((ValueDataSeriesProtoBuf.ValueDataSeriesForProto)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(ValueDataSeriesProtoBuf.ValueDataSeriesForProto other) {
        if (other == ValueDataSeriesProtoBuf.ValueDataSeriesForProto.getDefaultInstance()) return this;
        if (other.hasId()) {
          setId(other.getId());
        }
        if (other.hasUniformType()) {
          mergeUniformType(other.getUniformType());
        }
        if (valueListBuilder_ == null) {
          if (!other.valueList_.isEmpty()) {
            if (valueList_.isEmpty()) {
              valueList_ = other.valueList_;
              bitField0_ = (bitField0_ & ~0x00000004);
            } else {
              ensureValueListIsMutable();
              valueList_.addAll(other.valueList_);
            }
            onChanged();
          }
        } else {
          if (!other.valueList_.isEmpty()) {
            if (valueListBuilder_.isEmpty()) {
              valueListBuilder_.dispose();
              valueListBuilder_ = null;
              valueList_ = other.valueList_;
              bitField0_ = (bitField0_ & ~0x00000004);
              valueListBuilder_ = 
                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders ?
                   getValueListFieldBuilder() : null;
            } else {
              valueListBuilder_.addAllMessages(other.valueList_);
            }
          }
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }
      
      public final boolean isInitialized() {
        if (!hasId()) {
          
          return false;
        }
        if (!hasUniformType()) {
          
          return false;
        }
        if (!getUniformType().isInitialized()) {
          
          return false;
        }
        for (int i = 0; i < getValueListCount(); i++) {
          if (!getValueList(i).isInitialized()) {
            
            return false;
          }
        }
        return true;
      }
      
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder(
            this.getUnknownFields());
        while (true) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              this.setUnknownFields(unknownFields.build());
              onChanged();
              return this;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                this.setUnknownFields(unknownFields.build());
                onChanged();
                return this;
              }
              break;
            }
            case 10: {
              bitField0_ |= 0x00000001;
              id_ = input.readBytes();
              break;
            }
            case 18: {
              ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto.Builder subBuilder = ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto.newBuilder();
              if (hasUniformType()) {
                subBuilder.mergeFrom(getUniformType());
              }
              input.readMessage(subBuilder, extensionRegistry);
              setUniformType(subBuilder.buildPartial());
              break;
            }
            case 26: {
              ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto.Builder subBuilder = ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto.newBuilder();
              input.readMessage(subBuilder, extensionRegistry);
              addValueList(subBuilder.buildPartial());
              break;
            }
          }
        }
      }
      
      private int bitField0_;
      
      // required string id = 1;
      private java.lang.Object id_ = "";
      public boolean hasId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      public String getId() {
        java.lang.Object ref = id_;
        if (!(ref instanceof String)) {
          String s = ((com.google.protobuf.ByteString) ref).toStringUtf8();
          id_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      public Builder setId(String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        id_ = value;
        onChanged();
        return this;
      }
      public Builder clearId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        id_ = getDefaultInstance().getId();
        onChanged();
        return this;
      }
      void setId(com.google.protobuf.ByteString value) {
        bitField0_ |= 0x00000001;
        id_ = value;
        onChanged();
      }
      
      // required .ValueDataSeriesForProto.UniformTypeForProto uniformType = 2;
      private ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto uniformType_ = ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto.getDefaultInstance();
      private com.google.protobuf.SingleFieldBuilder<
          ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto, ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto.Builder, ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProtoOrBuilder> uniformTypeBuilder_;
      public boolean hasUniformType() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      public ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto getUniformType() {
        if (uniformTypeBuilder_ == null) {
          return uniformType_;
        } else {
          return uniformTypeBuilder_.getMessage();
        }
      }
      public Builder setUniformType(ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto value) {
        if (uniformTypeBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          uniformType_ = value;
          onChanged();
        } else {
          uniformTypeBuilder_.setMessage(value);
        }
        bitField0_ |= 0x00000002;
        return this;
      }
      public Builder setUniformType(
          ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto.Builder builderForValue) {
        if (uniformTypeBuilder_ == null) {
          uniformType_ = builderForValue.build();
          onChanged();
        } else {
          uniformTypeBuilder_.setMessage(builderForValue.build());
        }
        bitField0_ |= 0x00000002;
        return this;
      }
      public Builder mergeUniformType(ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto value) {
        if (uniformTypeBuilder_ == null) {
          if (((bitField0_ & 0x00000002) == 0x00000002) &&
              uniformType_ != ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto.getDefaultInstance()) {
            uniformType_ =
              ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto.newBuilder(uniformType_).mergeFrom(value).buildPartial();
          } else {
            uniformType_ = value;
          }
          onChanged();
        } else {
          uniformTypeBuilder_.mergeFrom(value);
        }
        bitField0_ |= 0x00000002;
        return this;
      }
      public Builder clearUniformType() {
        if (uniformTypeBuilder_ == null) {
          uniformType_ = ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto.getDefaultInstance();
          onChanged();
        } else {
          uniformTypeBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }
      public ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto.Builder getUniformTypeBuilder() {
        bitField0_ |= 0x00000002;
        onChanged();
        return getUniformTypeFieldBuilder().getBuilder();
      }
      public ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProtoOrBuilder getUniformTypeOrBuilder() {
        if (uniformTypeBuilder_ != null) {
          return uniformTypeBuilder_.getMessageOrBuilder();
        } else {
          return uniformType_;
        }
      }
      private com.google.protobuf.SingleFieldBuilder<
          ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto, ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto.Builder, ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProtoOrBuilder> 
          getUniformTypeFieldBuilder() {
        if (uniformTypeBuilder_ == null) {
          uniformTypeBuilder_ = new com.google.protobuf.SingleFieldBuilder<
              ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto, ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto.Builder, ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProtoOrBuilder>(
                  uniformType_,
                  getParentForChildren(),
                  isClean());
          uniformType_ = null;
        }
        return uniformTypeBuilder_;
      }
      
      // repeated .ValueDataSeriesForProto.ValueUnitForProto valueList = 3;
      private java.util.List<ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto> valueList_ =
        java.util.Collections.emptyList();
      private void ensureValueListIsMutable() {
        if (!((bitField0_ & 0x00000004) == 0x00000004)) {
          valueList_ = new java.util.ArrayList<ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto>(valueList_);
          bitField0_ |= 0x00000004;
         }
      }
      
      private com.google.protobuf.RepeatedFieldBuilder<
          ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto, ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto.Builder, ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProtoOrBuilder> valueListBuilder_;
      
      public java.util.List<ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto> getValueListList() {
        if (valueListBuilder_ == null) {
          return java.util.Collections.unmodifiableList(valueList_);
        } else {
          return valueListBuilder_.getMessageList();
        }
      }
      public int getValueListCount() {
        if (valueListBuilder_ == null) {
          return valueList_.size();
        } else {
          return valueListBuilder_.getCount();
        }
      }
      public ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto getValueList(int index) {
        if (valueListBuilder_ == null) {
          return valueList_.get(index);
        } else {
          return valueListBuilder_.getMessage(index);
        }
      }
      public Builder setValueList(
          int index, ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto value) {
        if (valueListBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureValueListIsMutable();
          valueList_.set(index, value);
          onChanged();
        } else {
          valueListBuilder_.setMessage(index, value);
        }
        return this;
      }
      public Builder setValueList(
          int index, ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto.Builder builderForValue) {
        if (valueListBuilder_ == null) {
          ensureValueListIsMutable();
          valueList_.set(index, builderForValue.build());
          onChanged();
        } else {
          valueListBuilder_.setMessage(index, builderForValue.build());
        }
        return this;
      }
      public Builder addValueList(ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto value) {
        if (valueListBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureValueListIsMutable();
          valueList_.add(value);
          onChanged();
        } else {
          valueListBuilder_.addMessage(value);
        }
        return this;
      }
      public Builder addValueList(
          int index, ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto value) {
        if (valueListBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureValueListIsMutable();
          valueList_.add(index, value);
          onChanged();
        } else {
          valueListBuilder_.addMessage(index, value);
        }
        return this;
      }
      public Builder addValueList(
          ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto.Builder builderForValue) {
        if (valueListBuilder_ == null) {
          ensureValueListIsMutable();
          valueList_.add(builderForValue.build());
          onChanged();
        } else {
          valueListBuilder_.addMessage(builderForValue.build());
        }
        return this;
      }
      public Builder addValueList(
          int index, ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto.Builder builderForValue) {
        if (valueListBuilder_ == null) {
          ensureValueListIsMutable();
          valueList_.add(index, builderForValue.build());
          onChanged();
        } else {
          valueListBuilder_.addMessage(index, builderForValue.build());
        }
        return this;
      }
      public Builder addAllValueList(
          java.lang.Iterable<? extends ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto> values) {
        if (valueListBuilder_ == null) {
          ensureValueListIsMutable();
          super.addAll(values, valueList_);
          onChanged();
        } else {
          valueListBuilder_.addAllMessages(values);
        }
        return this;
      }
      public Builder clearValueList() {
        if (valueListBuilder_ == null) {
          valueList_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000004);
          onChanged();
        } else {
          valueListBuilder_.clear();
        }
        return this;
      }
      public Builder removeValueList(int index) {
        if (valueListBuilder_ == null) {
          ensureValueListIsMutable();
          valueList_.remove(index);
          onChanged();
        } else {
          valueListBuilder_.remove(index);
        }
        return this;
      }
      public ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto.Builder getValueListBuilder(
          int index) {
        return getValueListFieldBuilder().getBuilder(index);
      }
      public ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProtoOrBuilder getValueListOrBuilder(
          int index) {
        if (valueListBuilder_ == null) {
          return valueList_.get(index);  } else {
          return valueListBuilder_.getMessageOrBuilder(index);
        }
      }
      public java.util.List<? extends ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProtoOrBuilder> 
           getValueListOrBuilderList() {
        if (valueListBuilder_ != null) {
          return valueListBuilder_.getMessageOrBuilderList();
        } else {
          return java.util.Collections.unmodifiableList(valueList_);
        }
      }
      public ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto.Builder addValueListBuilder() {
        return getValueListFieldBuilder().addBuilder(
            ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto.getDefaultInstance());
      }
      public ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto.Builder addValueListBuilder(
          int index) {
        return getValueListFieldBuilder().addBuilder(
            index, ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto.getDefaultInstance());
      }
      public java.util.List<ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto.Builder> 
           getValueListBuilderList() {
        return getValueListFieldBuilder().getBuilderList();
      }
      private com.google.protobuf.RepeatedFieldBuilder<
          ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto, ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto.Builder, ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProtoOrBuilder> 
          getValueListFieldBuilder() {
        if (valueListBuilder_ == null) {
          valueListBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<
              ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto, ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto.Builder, ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProtoOrBuilder>(
                  valueList_,
                  ((bitField0_ & 0x00000004) == 0x00000004),
                  getParentForChildren(),
                  isClean());
          valueList_ = null;
        }
        return valueListBuilder_;
      }
      
      // @@protoc_insertion_point(builder_scope:ValueDataSeriesForProto)
    }
    
    static {
      defaultInstance = new ValueDataSeriesForProto(true);
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:ValueDataSeriesForProto)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_ValueDataSeriesForProto_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_ValueDataSeriesForProto_fieldAccessorTable;
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_ValueDataSeriesForProto_UniformTypeForProto_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_ValueDataSeriesForProto_UniformTypeForProto_fieldAccessorTable;
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_ValueDataSeriesForProto_ValueUnitForProto_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_ValueDataSeriesForProto_ValueUnitForProto_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\027ValueDataSeriesProtoBuf\"\231\003\n\027ValueDataS" +
      "eriesForProto\022\n\n\002id\030\001 \002(\t\022A\n\013uniformType" +
      "\030\002 \002(\0132,.ValueDataSeriesForProto.Uniform" +
      "TypeForProto\022=\n\tvalueList\030\003 \003(\0132*.ValueD" +
      "ataSeriesForProto.ValueUnitForProto\032\201\001\n\023" +
      "UniformTypeForProto\022\016\n\006typeID\030\001 \002(\005\022\020\n\010t" +
      "ypeName\030\002 \002(\t\022H\n\010unitType\030\003 \002(\0162-.ValueD" +
      "ataSeriesForProto.DataUnitTypeForProto:\007" +
      "DEFAULT\0320\n\021ValueUnitForProto\022\014\n\004date\030\001 \002" +
      "(\t\022\r\n\005value\030\002 \002(\002\":\n\024DataUnitTypeForProt",
      "o\022\013\n\007DEFAULT\020\000\022\t\n\005VALUE\020\001\022\n\n\006STRING\020\002"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_ValueDataSeriesForProto_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_ValueDataSeriesForProto_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_ValueDataSeriesForProto_descriptor,
              new java.lang.String[] { "Id", "UniformType", "ValueList", },
              ValueDataSeriesProtoBuf.ValueDataSeriesForProto.class,
              ValueDataSeriesProtoBuf.ValueDataSeriesForProto.Builder.class);
          internal_static_ValueDataSeriesForProto_UniformTypeForProto_descriptor =
            internal_static_ValueDataSeriesForProto_descriptor.getNestedTypes().get(0);
          internal_static_ValueDataSeriesForProto_UniformTypeForProto_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_ValueDataSeriesForProto_UniformTypeForProto_descriptor,
              new java.lang.String[] { "TypeID", "TypeName", "UnitType", },
              ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto.class,
              ValueDataSeriesProtoBuf.ValueDataSeriesForProto.UniformTypeForProto.Builder.class);
          internal_static_ValueDataSeriesForProto_ValueUnitForProto_descriptor =
            internal_static_ValueDataSeriesForProto_descriptor.getNestedTypes().get(1);
          internal_static_ValueDataSeriesForProto_ValueUnitForProto_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_ValueDataSeriesForProto_ValueUnitForProto_descriptor,
              new java.lang.String[] { "Date", "Value", },
              ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto.class,
              ValueDataSeriesProtoBuf.ValueDataSeriesForProto.ValueUnitForProto.Builder.class);
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }
  
  // @@protoc_insertion_point(outer_class_scope)
}
