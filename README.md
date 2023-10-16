# Interzoid Data Matching Java SDK

This is a Java SDK for Interzoid's Generative-AI powered data matching, data quality, data cleansing, and data
normalization for organization and individual name data. Functions include the generation of similarity keys for
identifying and matching inconsistent name data, as well as comparing and scoring data for matching purposes.

The concept is that the same similarity key will be algorithmically generated for different permutations of the same data content, such as GE, Gen Elec, General Electric all generating the same similarity key. Then, these similarity keys can be used as the basis of matching data, identifying duplicates, and resolving inconsistencies that can otherwise degrade the usefulness and value of data-driven applications, processes, or anything else that makes use of data. These similarity keys form the basis of many of the different functions available in the SDK that make use of Generative AI, Machine Learning, specialized algorithms, and extensive knowledge bases - all in the Cloud - to provide its results. These include functions that generate similarity keys for custom use, functions that score matches for certain use cases, and functions that process and perform matching functions with entire database tables and datasets.

#### Table of Contents

1. [API Key](#api-key)
2. [Requirements](#requirements)
3. [Installation](#installation)
    1. [Gradle](#gradle)
    2. [Maven](#maven)
4. [Usage](#usage)
    1. [Using a custom OkHttpClient](#using-a-custom-okhttpclient)
5. [Data Matching APIs](#data-matching-apis)
    1. [Match Key APIs](#match-key-apis)
        1. [Full Name Match Key](#full-name-match-key)
        2. [Company Name Match Key](#company-name-match-key)
        3. [Address Match Key](#address-match-key)
    2. [Match Score Functions](#match-score-functions)
        1. [Full Name Match Score](#full-name-match-score)
        2. [Organization Name Match Score](#organization-name-match-score)
    3. [Account Information](#account-information)

## API Key

Please visit https://www.interzoid.com/register-api-account to register for an API key and receive free usage credits. This API key will be used as a parameter with each call to the API (via the SDK function) for authentication and usage tracking.

## Requirements

- Java 17 or higher

## Installation

### Gradle

```groovy
implementation 'com.interzoid:data-matching-sdk:1.0-SNAPSHOT'
```

### Maven

```xml
<dependencies>
    <dependency>
        <groupId>com.interzoid</groupId>
        <artifactId>data-matching-sdk</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
</dependencies>
```

## Usage

Individual APIs are grouped into API classes. Each API class has a Builder class that can be used to create an API that
can be reused for multiple requests.

```java
import com.interzoid.sdk.api.FullNameMatchKeyApi;

public class Main {
    public static void main(String[] args) {
        FullNameMatchKeyApi api = new FullNameMatchKeyApi.Builder().build();
        // create request and send it to the api
    }
}
```

### Using a custom OkHttpClient

The SDK uses [OkHttpClient](https://square.github.io/okhttp/) to make HTTP requests. If you want to use a custom
OkHttpClient, you have the option to
pass it to the API Builder classes. Otherwise, the SDK will create a default OkHttpClient for you.

```java
import com.interzoid.sdk.api.FullNameMatchKeyApi;
import okhttp3.*;

public class Main {
    public static void main(String[] args) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .connectionPool(new okhttp3.ConnectionPool(5, 10, TimeUnit.SECONDS))
                // other okHttpClient options
                .build();
        FullNameMatchKeyApi api = new FullNameMatchKeyApi.Builder()
                .withClient(okHttpClient)
                .build();
        // create request object and send it to the api
    }
}
```

## Data Matching APIs

Interzoid uses algorithmically generated similarity keys leveraging Generative AI, Large Language Models (LLMs) and
Machine Learning to intelligently match data within or across data sources.

To learn more about the technology behind these APIs, please
visit https://docs.interzoid.com/entries/understanding-data-matching

### Match Key APIs

#### Full Name Match Key

This API provides a hashed similarity key from the input data used to match with other similar full name data. Use the
generated similarity key, rather than the actual data itself, to match and/or sort individual name data by similarity.
This avoids the problems of data inconsistency, misspellings, and name variations when matching within a single dataset,
and can also help matching across datasets or for more advanced searching.

```java
import com.interzoid.sdk.api.FullNameMatchKeyApi;
import com.interzoid.sdk.model.FullNameMatchKeyRequest;
import com.interzoid.sdk.model.MatchKeyResponse;

public class Main {
    public static void main(String[] args) {
        FullNameMatchKeyApi api = new FullNameMatchKeyApi.Builder().build();

        FullNameMatchKeyRequest request =
                new FullNameMatchKeyRequest(
                        "YOUR_API_KEY_HERE",    // api key
                        "John Smith"            // fullname
                );

        try {
            MatchKeyResponse response = api.doRequest(request);
            System.out.println("Response: " + response.getSimKey());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

#### Company Name Match Key

This API provides a hashed similarity key from the input data used to match with other similar company name data. Use
the generated similarity key, rather than the actual data itself, to match and/or sort company name data to identify
inconsistently represented company and organization name data. This avoids the problems of data inconsistency,
misspellings, and name variations when matching within a single dataset or across multiple data sources.

The `MatchAlgorithm` parameter provides multiple matching algorithms:

- `WIDE` will find a greater number of matches, but may also find similarly-spelled false positives.
- `NARROW` will result in tighter matching requirements, but then may miss a few matches.
- `MEDIUM` is a balance between the two.
- Your business case will dictate which algorithm is ideal.

```java
import com.interzoid.sdk.api.CompanyNameMatchKeyApi;
import com.interzoid.sdk.model.CompanyNameMatchKeyRequest;
import com.interzoid.sdk.model.MatchKeyResponse;

public class Main {
    public static void main(String[] args) {
        CompanyNameMatchKeyApi api = new CompanyNameMatchKeyApi.Builder().build();

        CompanyNameMatchKeyRequest request =
                new CompanyNameMatchKeyRequest(
                        "YOUR_API_KEY_HERE",    // api key
                        "IBM",                  // company
                        CompanyNameMatchKeyRequest.MatchAlgorithm.WIDE
                );

        try {
            MatchKeyResponse response = api.doRequest(request);
            System.out.println("Response: " + response.getSimKey());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

#### Address Match Key

This API provides a hashed similarity key from the input data used to match with other similar address data. Use the
generated similarity key, rather than the actual data itself, to match and/or sort address data by similarity. This
avoids the problems of data inconsistency, misspellings, and address element variations when matching either withing a
single dataset, or across datasets. It also provides for broader searching capabilities.

You can choose from two `MatchAlgorithm` values: `WIDE` and `NARROW`.

- `NARROW` considers a unit number (suite, apartment, unit, etc.) when generating similarity keys. This ensures
  individual units are identified separately when comparing generated keys.
- `WIDE` parameter will not consider the unit numbers, generating matching similarity keys based on the primary address
  only.

```java
import com.interzoid.sdk.api.AddressMatchKeyApi;
import com.interzoid.sdk.model.AddressMatchKeyRequest;
import com.interzoid.sdk.model.MatchKeyResponse;

public class Main {
    public static void main(String[] args) {
        AddressMatchKeyApi api = new AddressMatchKeyApi.Builder().build();

        AddressMatchKeyRequest request =
                new AddressMatchKeyRequest(
                        "YOUR_API_KEY_HERE",    // api key
                        "100 Main Street",      // address1
                        AddressMatchKeyRequest.MatchAlgorithm.NARROW
                );

        try {
            MatchKeyResponse response = api.doRequest(request);
            System.out.println("Response: " + response.getSimKey());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### Match Score Functions

We provide 2 operations for match scoring: Organization name and Full name. The request params for these operations are
identical--provide 2 values and the API returns a matching score.

#### Full Name Match Score

This API provides a match score (likelihood of matching) between two individual names on a scale of 0-100, where 100 is
the highest possible match.

```java
import com.interzoid.sdk.api.FullNameMatchScoreApi;
import com.interzoid.sdk.model.FullNameMatchScoreRequest;
import com.interzoid.sdk.model.MatchScoreResponse;

public class Main {
    public static void main(String[] args) {
        FullNameMatchScoreApi api = new FullNameMatchScoreApi.Builder().build();

        FullNameMatchScoreRequest request =
                new FullNameMatchScoreRequest(
                        "YOUR_API_KEY_HERE",    // api key
                        "John Smith",           // name1
                        "John Smyth"            // name2
                );

        try {
            MatchScoreResponse response = api.doRequest(request);
            System.out.println("Response: " + response.getScore());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

#### Organization Name Match Score

This API provides a match score (likelihood of matching) from 0-100 between two organization names.

```java
import com.interzoid.sdk.api.OrganizationMatchScoreApi;
import com.interzoid.sdk.model.OrganizationMatchScoreRequest;
import com.interzoid.sdk.model.MatchScoreResponse;

public class Main {
    public static void main(String[] args) {
        OrganizationMatchScoreApi api = new OrganizationMatchScoreApi.Builder().build();

        OrganizationMatchScoreRequest request =
                new OrganizationMatchScoreRequest(
                        "YOUR_API_KEY_HERE",    // api key
                        "IBM",                  // org1
                        "IBM Corporation"       // org2
                );

        try {
            MatchScoreResponse response = api.doRequest(request);
            System.out.println("Response: " + response.getScore());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### Account Information

This API retrieves the current amount of remaining purchased (or trial) credits for a license key.

Using this function does **not** deduct credits from your account.

```java
import com.interzoid.sdk.api.AccountInfoApi;
import com.interzoid.sdk.model.InterzoidRequest;
import com.interzoid.sdk.model.InterzoidResponse;

public class Main {
    public static void main(String[] args) {
        AccountInfoApi api = new AccountInfoApi.Builder().build();
        InterzoidRequest request = new InterzoidRequest("YOUR_API_KEY");
        try {
            InterzoidResponse response = api.doRequest(request);
            System.out.println("Response: " + response.getCredits());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```
