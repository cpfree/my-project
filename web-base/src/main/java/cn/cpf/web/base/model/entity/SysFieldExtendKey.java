package cn.cpf.web.base.model.entity;

public class SysFieldExtendKey {
    private String schemaTag;

    private String tableName;

    private String name;

    public String getSchemaTag() {
        return schemaTag;
    }

    public void setSchemaTag(String schemaTag) {
        this.schemaTag = schemaTag == null ? null : schemaTag.trim();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}