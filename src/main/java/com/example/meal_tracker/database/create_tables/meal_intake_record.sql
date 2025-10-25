-- Table: public.meal_intake_record

-- DROP TABLE IF EXISTS public.meal_intake_record;

CREATE TABLE IF NOT EXISTS public.meal_intake_record
(
    intake_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    user_id integer NOT NULL,
    food_id integer NOT NULL,
    meal_timetype character varying(20) COLLATE pg_catalog."default" NOT NULL,
    grams numeric(7,2),
    carbohydrate numeric(7,2),
    protein numeric(7,2),
    fat numeric(7,2),
    sugar numeric(7,2),
    sodium numeric(7,2),
    cholesterol numeric(7,2),
    intake_date date NOT NULL,
    CONSTRAINT meal_intake_record_pkey PRIMARY KEY (intake_id),
    CONSTRAINT meal_intake_record_food_id_fkey FOREIGN KEY (food_id)
        REFERENCES public.food (food_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.meal_intake_record
    OWNER to postgres;
