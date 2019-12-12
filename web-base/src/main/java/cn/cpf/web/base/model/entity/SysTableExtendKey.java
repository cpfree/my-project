package cn.cpf.web.base.model.entity;

public class SysTableExtendKey {
    private String schemaTag;

    private String name;

    public String getSchemaTag() {
        return schemaTag;
    }

    public void setSchemaTag(String schemaTag) {
        this.schemaTag = schemaTag == null ? null : schemaTag.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}