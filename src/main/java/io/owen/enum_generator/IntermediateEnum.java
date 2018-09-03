package io.owen.enum_generator;

import io.owen.enum_generator.enums.EnumStyle;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by owen_q on 2018. 9. 3..
 */
public class IntermediateEnum {
    private String defaultClassName;
    private String givenClassName;

    private String description;

    private EnumStyle enumStyle;

    private Map<String, Object> declaredEnums = new HashMap<>();

    private IntermediateEnum() {

    }

    public IntermediateEnum(String defaultClassName, String givenClassName, String description, EnumStyle enumStyle, Map<String, Object> declaredEnums) {
        this.defaultClassName = defaultClassName;
        this.givenClassName = givenClassName;
        this.description = description;
        this.enumStyle = enumStyle;
        this.declaredEnums = declaredEnums;
    }

    public String getDefaultClassName() {
        return defaultClassName;
    }

    public void setDefaultClassName(String defaultClassName) {
        this.defaultClassName = defaultClassName;
    }

    public String getGivenClassName() {
        return givenClassName;
    }

    public void setGivenClassName(String givenClassName) {
        this.givenClassName = givenClassName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EnumStyle getEnumStyle() {
        return enumStyle;
    }

    public void setEnumStyle(EnumStyle enumStyle) {
        this.enumStyle = enumStyle;
    }

    public Map<String, Object> getDeclaredEnums() {
        return declaredEnums;
    }

    public void setDeclaredEnums(Map<String, Object> declaredEnums) {
        this.declaredEnums = declaredEnums;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("IntermediateEnum{");
        sb.append("defaultClassName='").append(defaultClassName).append('\'');
        sb.append(", givenClassName='").append(givenClassName).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", enumStyle=").append(enumStyle);
        sb.append(", declaredEnums=").append(declaredEnums);
        sb.append('}');
        return sb.toString();
    }

    public static class Builder{
        private String defaultClassName = "";
        private String givenClassName = "";

        private String description = "";

        private EnumStyle enumStyle = EnumStyle.ES6;

        private Map<String, Object> declaredEnums = new HashMap<>();


        public Builder defaultClassName(String defaultClassName){
            this.defaultClassName = defaultClassName;
            return this;
        }

        public Builder givenClassName(String givenClassName){
            this.givenClassName = givenClassName;
            return this;
        }

        public Builder description(String description){
            this.description = description;
            return this;
        }

        public Builder declaredEnums(Map<String, Object> declaredEnums){
            this.declaredEnums = declaredEnums;
            return this;
        }

        public IntermediateEnum build(){
            return new IntermediateEnum(this.defaultClassName, this.givenClassName, this.description, this.enumStyle, this.declaredEnums);
        }
    }
}
