package com.zhilian.market.entity.enums;

/**
 * 对象类型枚举
 * 类型 1 项目方案 2 项目绩效 3 项目动态 4项目总结 5项目案例 6项目faq 7会议  8实战走访 9辅助申请 10.宣传单页  11 视频 12 其它工具
 * 1 项目方案 2 项目绩效 3 项目动态 4项目总结 5项目案例 6项目faq 7会议  8实战走访 9辅助申请
 */
public enum EnclosureTypeEnum {
    /**
     * 项目方案
     */
    Program(1),
    /**
     * 项目绩效
     */
    Achievements(2),

    /**
     * 项目动态
     */
    Dynamic(3),

    /**
     * 4项目总结
     */
    Summary(4),
    /**
     * 5项目案例
     */
    Anli(5),
    /**
     * 6项目faq
     */
    FAQ(6),
    /**
     * 7会议
     */
    Meeting(7),
    /**
     * 8 实战走访
     */
    Visit(8),
    /**
     * 9辅导申请
     */
    Coach(9),
    /**
     * 10.宣传单页
     */
    Posts(10),
    /**
     * 101视频
     */
    Videos(11),
    /**
     * 12.其它工具
     */
    OtherTool(12);


        private int index;

        private EnclosureTypeEnum(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

}
