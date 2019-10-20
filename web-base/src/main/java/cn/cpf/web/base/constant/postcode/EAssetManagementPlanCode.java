package cn.cpf.web.base.constant.postcode;

import cn.cpf.web.base.lang.base.IPostCode;

public enum EAssetManagementPlanCode implements IPostCode {

    ampQuoteAddSuccess("0401", "发送报价成功!"),
    ampQuoteCopySuccess("0402", "复制报价成功!"),
    ampQuoteEditSuccess("0403", "修改报价成功!"),
    ampQuoteRevokeSuccess("0404", "撤消报价成功!"),
    ampQuoteNotFound("5001", "未发现相关报价信息!"),
    ampQuoteInvalid("5002", "该报价信息已过期!"),
    ampQuoteIsInvalid("5003", "该报价已经失效!"),
    ampQuoteIsRevoke("5004", "该报价已被撤消!"),
    ampTradeNotFound("5005", "未发现相关资管计划交易信息!"),
    ampTrade_quoterCancel("5006", "该交易已经被报价方取消!"),
    ampTrade_quoterRefuse("5007", "该交易已经被报价方拒绝!"),
    ampTrade_timeOut("5008", "该交易已经超时"),
    ampTrade_traderCancel("5009", "该交易已经被交易方取消!"),
    ampTrade_traderRefuse("5010", "该交易已经被交易方拒绝!"),
    ampTrade_clinch("5011", "该交易已成交!"),
    ampTrade_suspend("5012", "该交易已中止!"),
    sendingQuoteRepeat("5013", "您已有针对该报价的可用的交易数据, 请勿重复生成交易!");

    private String code;

    private String text;

    private String desc;

    EAssetManagementPlanCode(String code, String text, String desc) {
        this.code = code;
        this.text = text;
        this.desc = desc;
    }

    EAssetManagementPlanCode(String code, String text) {
        this(code, text, text);
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "{" + "code='" + code + '\'' + ", text='" + text + '\'' + ", desc='" + desc + '\'' + '}';
    }


}
