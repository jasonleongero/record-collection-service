SELECT
    artist_id,
    master_release_id,
    release_id,
    image_url,
    is_primary
FROM
    image
WHERE
    artist_id = ? AND
    master_release_id = ? AND
    release_id = ?