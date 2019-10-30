package cn.cpf.web.base.constant.dic;

import cn.cpf.web.base.lang.base.CodeItemPool;
import cn.cpf.web.base.lang.base.ICodeItem;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/4/2 14:51
 **/
public interface DicCommonOrganization {

    enum Role implements ICodeItem {

        Admin("Admin", "管理员"), Institution_Admin("Institution_Admin", "机构管理员"), Arbiter("Arbiter", "合规管理员"), Institution_ChiefDealer("Institution_ChiefDealer", "机构首席交易员"), Institution_Dealer("Institution_Dealer", "机构交易员"), ManageMoney("ManageMoney", "理财"), OfflineFunding("OfflineFunding", "线下资金"), OnlineFunding("OnlineFunding", "线上资金"), Bond("Bond", "承分销"), Goldloan("Goldloan", "贵金属租借"), Visitor("Visitor", "游客");

        Role(String code, String text) {
            CodeItemPool.putCodeItem(this, code, text);
        }
    }

}