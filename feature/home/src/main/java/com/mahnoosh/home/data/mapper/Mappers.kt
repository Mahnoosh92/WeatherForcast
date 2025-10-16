import com.mahnoosh.home.domain.model.Astro
import com.mahnoosh.home.domain.model.City
import com.mahnoosh.home.domain.model.Condition
import com.mahnoosh.home.domain.model.Current
import com.mahnoosh.home.domain.model.CurrentForcast
import com.mahnoosh.home.domain.model.CurrentWeather
import com.mahnoosh.home.domain.model.Day
import com.mahnoosh.home.domain.model.Forecast
import com.mahnoosh.home.domain.model.ForecastDay
import com.mahnoosh.home.domain.model.Hour
import com.mahnoosh.home.domain.model.Location
import com.mahnoosh.network.model.AstroDTO
import com.mahnoosh.network.model.CityDTO
import com.mahnoosh.network.model.ConditionDTO
import com.mahnoosh.network.model.CurrentDTO
import com.mahnoosh.network.model.CurrentForcastDTO
import com.mahnoosh.network.model.CurrentWeatherDTO
import com.mahnoosh.network.model.DayDTO
import com.mahnoosh.network.model.ForecastDTO
import com.mahnoosh.network.model.ForecastDayDTO
import com.mahnoosh.network.model.HourDTO
import com.mahnoosh.network.model.LocationDTO

/**
 * Converts a nullable AstroDTO to a non-nullable Astro domain model.
 * Uses default values for null fields.
 */

fun AstroDTO.toDomain() = Astro(
    sunrise = sunrise ?: "",
    sunset = sunset ?: "",
    moonrise = moonrise ?: "",
    moonset = moonset ?: "",
    moonPhase = moonPhase ?: "Unknown Phase",
    moonIllumination = moonIllumination ?: 0,
    isMoonUp = isMoonUp ?: 0,
    isSunUp = isSunUp ?: 0,
)

/**
 * Converts a non-nullable CityDTO to a non-nullable City domain model.
 * Since all fields are non-nullable in CityDTO, no defaulting is needed.
 */
fun CityDTO.toDomain() = City(
    id = id,
    name = name,
    region = region,
    country = country,
    lat = lat,
    lon = lon,
    url = url,
)

/**
 * Converts a nullable ConditionDTO to a non-nullable Condition domain model.
 * Uses default values for null fields.
 */
fun ConditionDTO.toDomain() = Condition(
    text = text ?: "Unknown Condition",
    icon = icon ?: "",
    code = code ?: 0
)

/**
 * Converts a nullable CurrentDTO to a non-nullable Current domain model.
 * Uses default values for null fields. Nested DTOs call their respective toDomain() mapper.
 */
fun CurrentDTO.toDomain() = Current(
    lastUpdatedEpoch = lastUpdatedEpoch ?: 0,
    lastUpdated = lastUpdated ?: "",
    tempC = tempC ?: 0.0,
    tempF = tempF ?: 0.0,
    isDay = isDay ?: 0,
    condition = condition.toDomain(), // ConditionDTO is non-nullable in CurrentDTO, so we map it directly
    windMph = windMph ?: 0.0,
    windKph = windKph ?: 0.0,
    windDegree = windDegree ?: 0,
    windDir = windDir ?: "",
    pressureMb = pressureMb ?: 0,
    pressureIn = pressureIn ?: 0.0,
    precipMm = precipMm ?: 0,
    precipIn = precipIn ?: 0,
    humidity = humidity ?: 0,
    cloud = cloud ?: 0,
    feelslikeC = feelslikeC ?: 0.0,
    feelslikeF = feelslikeF ?: 0.0,
    windchillC = windchillC ?: 0.0,
    windchillF = windchillF ?: 0.0,
    heatindexC = heatindexC ?: 0.0,
    heatindexF = heatindexF ?: 0.0,
    dewpointC = dewpointC ?: 0.0,
    dewpointF = dewpointF ?: 0.0,
    visKm = visKm ?: 0,
    visMiles = visMiles ?: 0,
    uv = uv ?: 0,
    gustMph = gustMph ?: 0.0,
    gustKph = gustKph ?: 0.0,
    shortRad = shortRad ?: 0,
    diffRad = diffRad ?: 0,
    dni = dni ?: 0,
    gti = gti ?: 0
)

/**
 * Converts CurrentForcastDTO to CurrentForcast domain model.
 */
fun CurrentForcastDTO.toDomain() = CurrentForcast(
    location = location.toDomain(),
    current = current.toDomain(),
    forecast = forecast.toDomain()
)

/**
 * Converts CurrentWeatherDTO to CurrentWeather domain model.
 * Safely handles nullable nested DTOs by mapping to default DTOs if null.
 */
fun CurrentWeatherDTO.toDomain() = CurrentWeather(
    location = location?.toDomain() ?: LocationDTO().toDomain(),
    current = current?.toDomain() ?: CurrentDTO().toDomain()
)

/**
 * Converts a nullable DayDTO to a non-nullable Day domain model.
 * Uses default values for null fields.
 */
fun DayDTO.toDomain() = Day(
    maxtempC = maxtempC ?: 0.0,
    maxtempF = maxtempF ?: 0,
    mintempC = mintempC ?: 0.0,
    mintempF = mintempF ?: 0.0,
    avgtempC = avgtempC ?: 0.0,
    avgtempF = avgtempF ?: 0.0,
    maxwindMph = maxwindMph ?: 0.0,
    maxwindKph = maxwindKph ?: 0.0,
    totalprecipMm = totalprecipMm ?: 0,
    totalprecipIn = totalprecipIn ?: 0,
    totalsnowCm = totalsnowCm ?: 0,
    avgvisKm = avgvisKm ?: 0,
    avgvisMiles = avgvisMiles ?: 0,
    avghumidity = avghumidity ?: 0,
    dailyWillItRain = dailyWillItRain ?: 0,
    dailyChanceOfRain = dailyChanceOfRain ?: 0,
    dailyWillItSnow = dailyWillItSnow ?: 0,
    dailyChanceOfSnow = dailyChanceOfSnow ?: 0,
    condition = condition?.toDomain() ?: ConditionDTO().toDomain(),
    uv = uv ?: 0.0
)

/**
 * Converts ForecastDTO to Forecast domain model.
 */
fun ForecastDTO.toDomain() = Forecast(
    forecastday = forecastday.map { it.toDomain() } // Map the list of DTOs
)

/**
 * Converts a nullable ForecastDayDTO to a non-nullable ForecastDay domain model.
 * Uses default values for null fields.
 */
fun ForecastDayDTO.toDomain() = ForecastDay(
    date = date ?: "",
    dateEpoch = dateEpoch ?: 0,
    day = day?.toDomain() ?: DayDTO().toDomain(),
    astro = astro?.toDomain() ?: AstroDTO().toDomain(),
    hour = hour.map { it.toDomain() } // Map the list of Hour DTOs
)

/**
 * Converts a nullable HourDTO to a non-nullable Hour domain model.
 * Uses default values for null fields.
 */
fun HourDTO.toDomain() = Hour(
    timeEpoch = timeEpoch ?: 0,
    time = time ?: "",
    tempC = tempC ?: 0.0,
    tempF = tempF ?: 0.0,
    isDay = isDay ?: 0,
    condition = condition?.toDomain() ?: ConditionDTO().toDomain(),
    windMph = windMph ?: 0.0,
    windKph = windKph ?: 0.0,
    windDegree = windDegree ?: 0,
    windDir = windDir ?: "",
    pressureMb = pressureMb ?: 0,
    pressureIn = pressureIn ?: 0.0,
    precipMm = precipMm ?: 0,
    precipIn = precipIn ?: 0,
    snowCm = snowCm ?: 0,
    humidity = humidity ?: 0,
    cloud = cloud ?: 0,
    feelslikeC = feelslikeC ?: 0.0,
    feelslikeF = feelslikeF ?: 0.0,
    windchillC = windchillC ?: 0.0,
    windchillF = windchillF ?: 0.0,
    heatindexC = heatindexC ?: 0.0,
    heatindexF = heatindexF ?: 0.0,
    dewpointC = dewpointC ?: 0.0,
    dewpointF = dewpointF ?: 0.0,
    willItRain = willItRain ?: 0,
    chanceOfRain = chanceOfRain ?: 0,
    willItSnow = willItSnow ?: 0,
    chanceOfSnow = chanceOfSnow ?: 0,
    visKm = visKm ?: 0,
    visMiles = visMiles ?: 0,
    gustMph = gustMph ?: 0.0,
    gustKph = gustKph ?: 0.0,
    uv = uv ?: 0,
    shortRad = shortRad ?: 0,
    diffRad = diffRad ?: 0,
    dni = dni ?: 0,
    gti = gti ?: 0
)

/**
 * Converts a nullable LocationDTO to a non-nullable Location domain model.
 * Uses default values for null fields.
 */
fun LocationDTO.toDomain() = Location(
    name = name ?: "Unknown Location",
    region = region ?: "",
    country = country ?: "",
    lat = lat ?: 0.0,
    lon = lon ?: 0.0,
    tzId = tzId ?: "",
    localtimeEpoch = localtimeEpoch ?: 0,
    localtime = localtime ?: ""
)
