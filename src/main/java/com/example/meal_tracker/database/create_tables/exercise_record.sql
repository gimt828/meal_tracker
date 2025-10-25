-- Table: public.exercise_record

-- DROP TABLE IF EXISTS public.exercise_record;

CREATE TABLE IF NOT EXISTS public.exercise_record
(
    exercise_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    user_id integer NOT NULL,
    exercise_type character varying(50) COLLATE pg_catalog."default",
    duration_min numeric(6,2),
    calories_burned numeric(10,2),
    exercise_date date NOT NULL,
    exercise_time time without time zone,
    CONSTRAINT exercise_record_pkey PRIMARY KEY (exercise_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.exercise_record
    OWNER to postgres;
