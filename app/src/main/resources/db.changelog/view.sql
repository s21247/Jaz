CREATE OR REPLACE VIEW auction_view as
SELECT
    au.id as auction_id,
    au.title as auctionTitle,
    au.price as price,
    ap.link as miniature
FROM
    auction au, photo ap
WHERE ap.order = 1 AND ap.auction_id=au.id;