package com.programming.languages.repository.http.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class Item(
        @JsonProperty("stargazers_count")
        val stars: Int,
        val forks: Int,
        val watchers: Int,
        @JsonProperty("open_issues")
        val openIssues: Int
)
