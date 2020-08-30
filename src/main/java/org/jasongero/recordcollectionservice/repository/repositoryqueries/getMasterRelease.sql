SELECT
    mr.artist_id,
    mr.master_release_id,
    mr.title,
    i.image_url AS primary_image_url,
    (
        SELECT
            COUNT(release_id)
        FROM
            `release` AS ri
        WHERE
            ri.master_release_id = mr.master_release_id
    ) AS release_versions_count
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
    mr.master_release_id = ?