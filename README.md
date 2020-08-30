# Record Collection Service
This is a service that fetches information from the record collection database via several endpoints which it provides.  This document catalogs these endpoints, the form of the requests they receive, and the responses they provide.
## Endpoints
### `GET /releases/{id}`
Get a resource by its ID.
Example response body:
```
{
    "artist_id": 1,
    "master_release_id": 1,
    "release_id": 1,
    "release_year": 1985,
    "release_month": 5,
    "release_day": 13,
    "title": "The Perfect Kiss",
    "catalog_number": "FAC 123",
    "country_iso_code": "GBR",
    "id_record_label": 1
}
```