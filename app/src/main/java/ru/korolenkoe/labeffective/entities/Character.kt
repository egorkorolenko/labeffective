package ru.korolenkoe.labeffective.entities

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.korolenkoe.labeffective.utils.ThumbnailConverter

@JsonClass(generateAdapter = true)
@Entity(tableName = "characters")
@Keep
data class Character(
    @Json(name = "id")
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,
    @Json(name = "name")
    @ColumnInfo(name = "name")
    val name: String,
    @Json(name = "description")
    @ColumnInfo(name = "description")
    val description: String,
    @Json(name = "thumbnail")
    @ColumnInfo(name = "thumbnail", typeAffinity = ColumnInfo.BLOB)
    @TypeConverters(ThumbnailConverter::class)
    val thumbnail: Thumbnail?
)
