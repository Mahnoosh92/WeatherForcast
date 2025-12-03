package com.mahnoosh.datastore

import com.google.common.truth.Truth.assertThat
import com.mahnoosh.model.data.ThemeBrand
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class UserPreferencesDatastoreTest {

    private lateinit var userPreferencesDatastore: UserPreferencesDatastore

    @Before
    fun setUp() {
        userPreferencesDatastore =
            UserPreferencesDatastore(InMemoryDataStore(UserPreferences.getDefaultInstance()))
    }

    @Test
    fun shouldHideOnboardingIsFalseByDefault() = runTest {
        assertThat(userPreferencesDatastore.userData.first().shouldHideOnboarding).isFalse()
    }

    @Test
    fun userShouldHideOnboardingIsTrueWhenSet() = runTest {
        userPreferencesDatastore.setShouldHideOnboarding(true)

        assertThat(userPreferencesDatastore.userData.first().shouldHideOnboarding).isTrue()
    }

    @Test
    fun userThemeBrandValueChangedToWhatSet() = runTest{
        userPreferencesDatastore.setThemeBrand(ThemeBrand.ANDROID)

        assertThat(userPreferencesDatastore.userData.first().themeBrand).isEqualTo(ThemeBrand.ANDROID)
    }
}