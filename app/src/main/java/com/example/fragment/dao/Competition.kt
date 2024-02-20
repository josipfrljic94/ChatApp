package com.example.fragment.dao

data class Competition(
    val attendance: Int,
    val broadcasts: List<Broadcast>,
    val competitors: List<Competitor>,
    val conferenceCompetition: Boolean,
    val date: String,
    val format: Format,
    val geoBroadcasts: List<GeoBroadcast>,
    val id: String,
    val neutralSite: Boolean,
    val notes: List<Any>,
    val playByPlayAvailable: Boolean,
    val recent: Boolean,
    val startDate: String,
    val status: Status,
    val tickets: List<Ticket>,
    val timeValid: Boolean,
    val type: TypeXX,
    val uid: String,
    val venue: Venue,
    val wasSuspended: Boolean
)