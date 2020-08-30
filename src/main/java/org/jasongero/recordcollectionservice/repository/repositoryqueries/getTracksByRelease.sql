SELECT
    release_id,
    `order`,
    track_number,
    duration,
    title
FROM
    track
WHERE
    release_id = ?
ORDER BY
    `order`