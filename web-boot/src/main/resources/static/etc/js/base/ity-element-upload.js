

let ityElementUpload = {
    handlePreview: function (file) {
        // $.sendPostRequest('attachment/download', {fileName : file.name,filePath : file.url,online : false},
        // true, true);
        window.open(file.url);
    },
    handleRemove: function (file, fileList) {
        if (file.fileGuid){
            $.sendPostRequest('attachment/remove', {fileGuid : file.fileGuid, packageGuid : this.formValues.attachmentGuid},
                true, true);
        }
    },
    handleExceed: function (files, fileList) {
        this.$message.warning('最多选择' + this.uploadData.limit + '个文件!');
    },
    beforeUpload: function (file) {
        let that = this;
        // 文件类型
        let fileName = file.name.substring(file.name.lastIndexOf('.'));
        let acceptType = this.uploadData.acceptType;
        if (fileName !== this.uploadData.acceptType) {
            that.uploadTemplateDialog = false;
            that.$message({
                type: 'error',
                showClose: true,
                duration: 3000,
                message: '只能上传' + acceptType.substring(1) + '格式文件!'
            });
            return false;
        }
        //读取文件大小
        let fileSize = file.size;
        if (fileSize > 10485760) {
            that.uploadTemplateDialog = false;
            that.$message({
                type: 'error',
                showClose: true,
                duration: 3000,
                message: '文件大小不能大于10M!'
            });
            return false;
        }
        this.uploadData.params.packageGuid = this.formValues.attachmentGuid;
        return true;
    },
    uploadSuccess: function (response, file, fileList) {
        if (response.status.code === '0000') {
            file.url = this.uploadData.params.mappingPath + response.data.filePath;
            file.fileGuid = response.data.fileGuid;
            file.packageGuid = response.data.packageGuid;
            this.formValues.attachmentGuid = response.data.packageGuid;
        } else {
            layer.msg('上传失败');
        }
    }
};