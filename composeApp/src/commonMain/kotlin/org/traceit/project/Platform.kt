package org.traceit.project

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform