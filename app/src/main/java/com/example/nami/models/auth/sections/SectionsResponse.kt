package com.example.nami.models.auth.sections

data class SectionsResponse (
    val legends: List<Legend>,
    val sections: List<SectionResponse>,
    val message:String
)

data class Legend (
    val id: Long,
    val name: String,
    val description: String? = null,
    val color: String,
    val visible: Boolean
)

data class SectionResponse (
    val id: Long,
    val name: String,
    val color: String,
    val legends: List<Long>
)