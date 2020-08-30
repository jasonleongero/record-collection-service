SELECT
    release_id,
    format_name,
    quantity,
    notes
FROM
    format
WHERE
    release_id = ?