-- Table: public.books

-- DROP TABLE public.books;

CREATE TABLE public.books
(
    book_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( CYCLE INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    author character varying(100) COLLATE pg_catalog."default" NOT NULL,
    title character varying(100) COLLATE pg_catalog."default" NOT NULL,
    publisher character varying(30) COLLATE pg_catalog."default" NOT NULL,
    year_of_publishing integer NOT NULL,
    CONSTRAINT "Books_pkey" PRIMARY KEY (book_id)
)

TABLESPACE pg_default;

ALTER TABLE public.books
    OWNER to postgres;
	
INSERT INTO public.books(
	author, title, publisher, year_of_publishing)
	VALUES ('Antoine de Saint-Exupery', 'Little Prince', 'Wordsworth', 2018);
	
INSERT INTO public.books(
	author, title, publisher, year_of_publishing)
	VALUES ('George Orwell', 'Animal Farm', 'Wordsworth', 2021);
	
INSERT INTO public.books(
	author, title, publisher, year_of_publishing)
	VALUES ('Jane Austen', 'Emma', 'Vintage Classics', 2016);
	
INSERT INTO public.books(
	author, title, publisher, year_of_publishing)
	VALUES ('George Orwell', '1984 (Nineteen Eighty-Four) (Anniversary Edition)', 'Penguin', 2009);
	
INSERT INTO public.books(
	author, title, publisher, year_of_publishing)
	VALUES ('Margaret Mitchell', 'Gone with the Wind', 'Pan', 2014);
	
INSERT INTO public.books(
	author, title, publisher, year_of_publishing)
	VALUES ('George Orwell', '1984 (Nineteen Eighty-Four)', 'William Collins', 2021);
	
INSERT INTO public.books(
	author, title, publisher, year_of_publishing)
	VALUES ('Daniel Defoe', 'Robinson Crusoe', 'Welbeck', 2021);
	
INSERT INTO public.books(
	author, title, publisher, year_of_publishing)
	VALUES ('J. D. Salinger', 'The Catcher in the Rye', 'Penguin', 2010);
	
INSERT INTO public.books(
	author, title, publisher, year_of_publishing)
	VALUES ('George Orwell', 'Animal Farm', 'Alma Classics', 2021);
	
INSERT INTO public.books(
	author, title, publisher, year_of_publishing)
	VALUES ('George Orwell', 'Animal Farm', 'Penguin Classics', 2018);