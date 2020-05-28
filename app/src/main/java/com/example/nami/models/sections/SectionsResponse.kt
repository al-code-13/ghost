package com.example.nami.models.sections

data class SectionsResponse(
    val actions: List<Action>,
    val legends: List<Legend>,
    val sections: List<SectionResponse>,
    val message: String?

)

data class Action(
    val id: Long,
    val name: String,
    val description: String? = null,
    val destructive: Boolean
)

data class Legend(
    val id: Long,
    val name: String,
    val description: String? = null,
    val color: String,
    val visible: Boolean,
    val actions: List<Long>? = null,
    val action: List<Long>? = null
)


data class SectionResponse(
    val id: Long,
    val name: String,
    val color: String,
    val legends: List<Long>
)