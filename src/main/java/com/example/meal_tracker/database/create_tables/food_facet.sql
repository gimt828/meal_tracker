-- Table: public.food_facet

-- DROP TABLE IF EXISTS public.food_facet;

CREATE TABLE IF NOT EXISTS public.food_facet
(
    food_id bigint NOT NULL,
    facet_value_id bigint NOT NULL,
    CONSTRAINT food_facet_pkey PRIMARY KEY (food_id, facet_value_id),
    CONSTRAINT food_facet_facet_value_id_fkey FOREIGN KEY (facet_value_id)
        REFERENCES public.facet_value (facet_value_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT food_facet_food_id_fkey FOREIGN KEY (food_id)
        REFERENCES public.food (food_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.food_facet
    OWNER to postgres;
