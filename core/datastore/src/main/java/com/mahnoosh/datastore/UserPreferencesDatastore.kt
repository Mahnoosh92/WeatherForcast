package com.mahnoosh.datastore

import androidx.datastore.core.DataStore
import com.mahnoosh.model.data.ThemeBrand
import com.mahnoosh.model.data.UserData
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferencesDatastore @Inject constructor(private val userPreferences: DataStore<UserPreferences>) {
    val userData = userPreferences.data
        .map {
            UserData(
                themeBrand = when (it.themeBrand) {
                    null,
                    ThemeBrandProto.THEME_BRAND_UNSPECIFIED,
                    ThemeBrandProto.UNRECOGNIZED,
                    ThemeBrandProto.THEME_BRAND_DEFAULT,
                        -> ThemeBrand.DEFAULT

                    ThemeBrandProto.THEME_BRAND_ANDROID -> ThemeBrand.ANDROID
                },
                shouldHideOnboarding = it.shouldHideOnboarding
            )
        }

    suspend fun setThemeBrand(themeBrand: ThemeBrand) {
        userPreferences.updateData {
            it.copy {
                this.themeBrand = when (themeBrand) {
                    ThemeBrand.DEFAULT -> ThemeBrandProto.THEME_BRAND_DEFAULT

                    ThemeBrand.ANDROID -> ThemeBrandProto.THEME_BRAND_ANDROID
                }
            }
        }
    }

    suspend fun setShouldHideOnboarding(shouldHideOnboarding: Boolean) {
        userPreferences.updateData {
            it.copy {
                this.shouldHideOnboarding = shouldHideOnboarding
            }
        }
    }
}