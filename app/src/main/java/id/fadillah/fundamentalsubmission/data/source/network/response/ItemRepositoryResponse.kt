package id.fadillah.fundamentalsubmission.data.source.network.response

import com.google.gson.annotations.SerializedName

data class ItemRepositoryResponse(

	@field:SerializedName("actor")
	val actor: Actor,

	@field:SerializedName("public")
	val jsonMemberPublic: Boolean,

	@field:SerializedName("payload")
	val payload: Payload,

	@field:SerializedName("repo")
	val repo: Repo,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("type")
	val type: String
)

data class Actor(

	@field:SerializedName("display_login")
	val displayLogin: String,

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("gravatar_id")
	val gravatarId: String,

	@field:SerializedName("url")
	val url: String
)

data class Payload(

	@field:SerializedName("forkee")
	val forkee: Forkee
)

data class Repo(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("url")
	val url: String
)

data class Forkee(

	@field:SerializedName("stargazers_count")
	val stargazersCount: Int,

	@field:SerializedName("pushed_at")
	val pushedAt: String,

	@field:SerializedName("subscription_url")
	val subscriptionUrl: String,

	@field:SerializedName("language")
	val language: Any,

	@field:SerializedName("branches_url")
	val branchesUrl: String,

	@field:SerializedName("issue_comment_url")
	val issueCommentUrl: String,

	@field:SerializedName("labels_url")
	val labelsUrl: String,

	@field:SerializedName("subscribers_url")
	val subscribersUrl: String,

	@field:SerializedName("releases_url")
	val releasesUrl: String,

	@field:SerializedName("svn_url")
	val svnUrl: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("forks")
	val forks: Int,

	@field:SerializedName("archive_url")
	val archiveUrl: String,

	@field:SerializedName("git_refs_url")
	val gitRefsUrl: String,

	@field:SerializedName("forks_url")
	val forksUrl: String,

	@field:SerializedName("statuses_url")
	val statusesUrl: String,

	@field:SerializedName("ssh_url")
	val sshUrl: String,

	@field:SerializedName("license")
	val license: License,

	@field:SerializedName("full_name")
	val fullName: String,

	@field:SerializedName("size")
	val size: Int,

	@field:SerializedName("languages_url")
	val languagesUrl: String,

	@field:SerializedName("html_url")
	val htmlUrl: String,

	@field:SerializedName("collaborators_url")
	val collaboratorsUrl: String,

	@field:SerializedName("clone_url")
	val cloneUrl: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("pulls_url")
	val pullsUrl: String,

	@field:SerializedName("default_branch")
	val defaultBranch: String,

	@field:SerializedName("hooks_url")
	val hooksUrl: String,

	@field:SerializedName("trees_url")
	val treesUrl: String,

	@field:SerializedName("tags_url")
	val tagsUrl: String,

	@field:SerializedName("private")
	val jsonMemberPrivate: Boolean,

	@field:SerializedName("contributors_url")
	val contributorsUrl: String,

	@field:SerializedName("has_downloads")
	val hasDownloads: Boolean,

	@field:SerializedName("notifications_url")
	val notificationsUrl: String,

	@field:SerializedName("open_issues_count")
	val openIssuesCount: Int,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("watchers")
	val watchers: Int,

	@field:SerializedName("keys_url")
	val keysUrl: String,

	@field:SerializedName("deployments_url")
	val deploymentsUrl: String,

	@field:SerializedName("has_projects")
	val hasProjects: Boolean,

	@field:SerializedName("archived")
	val archived: Boolean,

	@field:SerializedName("has_wiki")
	val hasWiki: Boolean,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("public")
	val jsonMemberPublic: Boolean,

	@field:SerializedName("comments_url")
	val commentsUrl: String,

	@field:SerializedName("stargazers_url")
	val stargazersUrl: String,

	@field:SerializedName("disabled")
	val disabled: Boolean,

	@field:SerializedName("git_url")
	val gitUrl: String,

	@field:SerializedName("has_pages")
	val hasPages: Boolean,

	@field:SerializedName("owner")
	val owner: Owner,

	@field:SerializedName("commits_url")
	val commitsUrl: String,

	@field:SerializedName("compare_url")
	val compareUrl: String,

	@field:SerializedName("git_commits_url")
	val gitCommitsUrl: String,

	@field:SerializedName("blobs_url")
	val blobsUrl: String,

	@field:SerializedName("git_tags_url")
	val gitTagsUrl: String,

	@field:SerializedName("merges_url")
	val mergesUrl: String,

	@field:SerializedName("downloads_url")
	val downloadsUrl: String,

	@field:SerializedName("has_issues")
	val hasIssues: Boolean,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("contents_url")
	val contentsUrl: String,

	@field:SerializedName("mirror_url")
	val mirrorUrl: Any,

	@field:SerializedName("milestones_url")
	val milestonesUrl: String,

	@field:SerializedName("teams_url")
	val teamsUrl: String,

	@field:SerializedName("fork")
	val fork: Boolean,

	@field:SerializedName("issues_url")
	val issuesUrl: String,

	@field:SerializedName("events_url")
	val eventsUrl: String,

	@field:SerializedName("issue_events_url")
	val issueEventsUrl: String,

	@field:SerializedName("assignees_url")
	val assigneesUrl: String,

	@field:SerializedName("open_issues")
	val openIssues: Int,

	@field:SerializedName("watchers_count")
	val watchersCount: Int,

	@field:SerializedName("node_id")
	val nodeId: String,

	@field:SerializedName("homepage")
	val homepage: String,

	@field:SerializedName("forks_count")
	val forksCount: Int
)

data class License(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("spdx_id")
	val spdxId: String,

	@field:SerializedName("key")
	val key: String,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("node_id")
	val nodeId: String
)

data class Owner(

	@field:SerializedName("gists_url")
	val gistsUrl: String,

	@field:SerializedName("repos_url")
	val reposUrl: String,

	@field:SerializedName("following_url")
	val followingUrl: String,

	@field:SerializedName("starred_url")
	val starredUrl: String,

	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("followers_url")
	val followersUrl: String,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("subscriptions_url")
	val subscriptionsUrl: String,

	@field:SerializedName("received_events_url")
	val receivedEventsUrl: String,

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("events_url")
	val eventsUrl: String,

	@field:SerializedName("html_url")
	val htmlUrl: String,

	@field:SerializedName("site_admin")
	val siteAdmin: Boolean,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("gravatar_id")
	val gravatarId: String,

	@field:SerializedName("node_id")
	val nodeId: String,

	@field:SerializedName("organizations_url")
	val organizationsUrl: String
)
