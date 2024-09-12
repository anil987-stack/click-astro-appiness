package com.android.appinesstask.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey


data class ImagePath(
    val square: String,
    val wide: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        square = parcel.readString() ?: "",
        wide = parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(square)
        parcel.writeString(wide)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ImagePath> {
        override fun createFromParcel(parcel: Parcel): ImagePath {
            return ImagePath(parcel)
        }

        override fun newArray(size: Int): Array<ImagePath?> {
            return arrayOfNulls(size)
        }
    }
}

data class ApiResponse(
    val status: String,
    val products: Map<String, ProductModel>
)

@Entity(tableName = "products")
data class ProductModel(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val availableLanguages: List<String>,
    val pages: Int,
    val pagesintext: String,
    val report_type: String,
    val authentic: String,
    val remedies: String,
    val vedic: String,
    val price: Double,
    val discount: Double,
    val appDiscount: Double,
    val couponDiscount: Double,
    val imagePath: ImagePath,
    val soldcount: String,
    val avg: Double
): Parcelable {
    constructor(parcel: Parcel) : this(
        id = parcel.readString() ?: "",
        name = parcel.readString() ?: "",
        description = parcel.readString() ?: "",
        pages = parcel.readInt(),
        pagesintext = parcel.readString() ?: "",
        report_type = parcel.readString() ?: "",
        authentic = parcel.readString() ?: "",
        remedies = parcel.readString() ?: "",
        vedic = parcel.readString() ?: "",
        price = parcel.readDouble(),
        discount = parcel.readDouble(),
        appDiscount = parcel.readDouble(),
        couponDiscount = parcel.readDouble(),
        imagePath = parcel.readParcelable(ImagePath::class.java.classLoader) ?: ImagePath("", ""),
        soldcount = parcel.readString() ?: "",
        avg = parcel.readDouble(),
        availableLanguages = parcel.createStringArrayList() ?: emptyList(),

        )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeInt(pages)
        parcel.writeString(pagesintext)
        parcel.writeString(report_type)
        parcel.writeString(authentic)
        parcel.writeString(remedies)
        parcel.writeString(vedic)
        parcel.writeDouble(price)
        parcel.writeDouble(discount)
        parcel.writeDouble(appDiscount)
        parcel.writeDouble(couponDiscount)
        parcel.writeParcelable(imagePath, flags)
        parcel.writeString(soldcount)
        parcel.writeDouble(avg)
        parcel.writeStringList(availableLanguages)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductModel> {
        override fun createFromParcel(parcel: Parcel): ProductModel {
            return ProductModel(parcel)
        }

        override fun newArray(size: Int): Array<ProductModel?> {
            return arrayOfNulls(size)
        }
    }
}
