package org.williams.project.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Williams
 * @date 2024-04-18-20:14
 * @Description 內容类型
 */
@Getter
@AllArgsConstructor
public enum ContentTypeEnum {

    MATTER(1), // 村民说事
    VILLAGE_TYFON(2), // 大喇叭
    OPEN_LETTER(3), // 书记公开信
    VILLAGE_EVENT(4), // 大事记
    PARTY_DIARY(5), // 党群服务中心
    WORK_NOTICE(6), // 通知
    VILLAGE_THREE_EVENT(7), // 三务公开

    TRANSACTION(8), // 清单
    ACTIVITY(9), // 活动

    PUBLICITY(10), // 积分公示
    REPORT(11), // 积分申报
    SCORE_RANK(12), // 积分排行
    OPEN_SHOP(13), // 开启超市
    SHOP_ADD(14), // 超市上架
    SHOP_EXCHANGE(15), // 超市兑换
    PROVISION(16), // 村规民约
    PROVISION_VOTE(17), // 村规民约表决
    SCORE_DETAIL(18), // 积分细则
    SCORE_BONUS(19), // 积分分红
    COMMITTEE(20),//关工委
    MARKET(21),//赶集
    ;


    private final int type;


    public static ContentTypeEnum enumByType(Integer type) {
        if (type != null) {
            for (ContentTypeEnum value : ContentTypeEnum.values()) {
                if (value.type == type) {
                    return value;
                }
            }
        }
        return null;
    }
}
