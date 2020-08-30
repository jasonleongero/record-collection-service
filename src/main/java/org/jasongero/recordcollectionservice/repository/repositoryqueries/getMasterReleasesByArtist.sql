-- Order by the minimum release date of each master release's child release in ascending order.

SELECT
    mr.artist_id AS artist_id,
    mr.master_release_id AS master_release_id,
    mr.title,
    i.image_url AS primary_image_url,
    (
        SELECT
            COUNT(release_id)
        FROM
            `release` AS ri
        WHERE
            ri.master_release_id = mr.master_release_id
    ) AS release_versions_count,
    (
        SELECT
            MIN(normalized_release_date)
        FROM
            (
                SELECT
                    rii.master_release_id,
                    IF(
                        rii.release_year = NULL,
                        NULL,
                        UNIX_TIMESTAMP(
                            CONCAT(
                                CONCAT(rii.release_year, '-'),
                                CONCAT(IFNULL(rii.release_month, '01'), '-'),
                                IFNULL(rii.release_day, '01')
                            )
                        ) 
                    ) AS normalized_release_date
                FROM
                    `release` AS rii
            ) AS dt
        GROUP BY
            dt.master_release_id
        HAVING
            dt.master_release_id = mr.master_release_id
    ) AS normalized_initial_release_date
FROM
    master_release AS mr
LEFT JOIN
    `release` AS r
ON
    r.master_release_id = mr.master_release_id AND
    r.is_key = 1
LEFT JOIN
    image AS i
ON
    i.release_id = r.release_id AND
    i.is_primary = 1
WHERE
    mr.artist_id = ?
ORDER BY
    -normalized_initial_release_date DESC