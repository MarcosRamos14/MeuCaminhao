package com.dys.mobile.meucaminhao.domain.enum

enum class ChecklistStatusEnum {
    COMPLETED,
    INCOMPLETE,
    UNREALIZED;

    companion object {
        fun from(value: String?): ChecklistStatusEnum?
                = ChecklistStatusEnum.entries.associateBy(ChecklistStatusEnum::name)[value]
    }
}