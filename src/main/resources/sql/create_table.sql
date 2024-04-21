DROP SCHEMA IF EXISTS libraryy CASCADE;
CREATE SCHEMA libraryy;

CREATE TABLE libraryy.books (
                       "book_id" SERIAL,
                       "name" text not null unique ,
                       "author" text not null ,
                       "publishing_house" text not null ,
                       "amount" int not null ,
                       "about" text,
                       "genre" text not null ,
                       "date_of_receiving" date not null ,
                       PRIMARY KEY ("book_id")
);

CREATE TABLE libraryy.book_copy (
                           "book_id" int not null ,
                           "copy_id" SERIAL,
                           "is_taken_now" text,
                           PRIMARY KEY ("copy_id")
);

CREATE TABLE libraryy.records (
                         "record_id" SERIAL,
                         "reader_id" int not null ,
                         "copy_id" int not null ,
                         "taking_date" date not null ,
                         "returning_date" date ,
                         PRIMARY KEY ("record_id")
);

CREATE TABLE libraryy.reader (
                        "reader_id" SERIAL,
                        "name" text not null ,
                        "surname" text not null unique ,
                        "patronymic" text,
                        "card_date" date not null ,
                        "date_of_birth" date,
                        "address" text,
                        "phone_number" varchar,
                        PRIMARY KEY ("reader_id")
);
