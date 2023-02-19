create table bids
(
    id        bigint auto_increment
        primary key,
    amount    double null,
    bidder_id bigint null,
    item_id   bigint null
)
    engine = MyISAM;

create index FKg1mdb2uha9v6t2ujkvlmj3tuq
    on bids (item_id);

create index FKmtrc6tnwawlpk1u2km6qnxbha
    on bids (bidder_id);

create table items
(
    id             bigint auto_increment
        primary key,
    description    varchar(255) null,
    name           varchar(255) null,
    photourl       varchar(255) null,
    purchase_price double       null,
    sellable       bit          null,
    starting_price double       null,
    buyer_id       bigint       null,
    seller_id      bigint       null
)
    engine = MyISAM;

create index FKitoq088n4b4sdl1ibkv6uj61v
    on items (buyer_id);

create index FKsm9ro5ntn6yaav2m7ydato0fc
    on items (seller_id);

create table users
(
    id       bigint auto_increment
        primary key,
    balance  double       null,
    password varchar(255) null,
    username varchar(255) null
)
    engine = MyISAM;

