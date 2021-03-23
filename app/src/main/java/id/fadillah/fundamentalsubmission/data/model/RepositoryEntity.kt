package id.fadillah.fundamentalsubmission.data.model

data class RepositoryEntity(
    val id: Int = 0,
    val name: String,
    val description: String? = null,
    val createAt: String? = null,
    val language: String? = null,
    val stargazer: Int = 0
)