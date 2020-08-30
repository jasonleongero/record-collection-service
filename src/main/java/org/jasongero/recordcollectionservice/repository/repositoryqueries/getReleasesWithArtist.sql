-- Sort by release date; releases with no release date are ordered last.

SELECT
    a.artist_id,
    a.name AS artist_name,
    mr.master_release_id,
    r.release_id,
    r.release_year,
    r.release_month,
    r.release_day,
    r.title,
    r.catalog_number,
    r.country_iso_code,
    i.image_url AS primary_image_url,
    rl.name AS record_label_name,
    c.name AS country_name,
    IF(
        r.release_year = NULL,
        NULL,
        UNIX_TIMESTAMP(
            CONCAT(
                CONCAT(r.release_year, '-'),
                CONCAT(IFNULL(r.release_month, '01'), '-'),
                IFNULL(r.release_day, '01')
            )
        ) 
    ) AS normalized_release_date
FROM
    `release` AS r
LEFT JOIN
    master_release AS mr
ON
    mr.master_release_id = r.master_release_id
LEFT JOIN
    artist AS a
ON
    mr.artist_id = a.artist_id
LEFT JOIN
    record_label AS rl
ON
    r.record_label_id = rl.record_label_id
LEFT JOIN
    country AS c
ON
    r.country_iso_code = c.country_iso_code
LEFT JOIN
    image AS i
ON
    r.release_id = i.release_id AND
    i.is_primary = 1
ORDER BY
    -normalized_release_date DESC