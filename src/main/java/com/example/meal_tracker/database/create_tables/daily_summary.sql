-- Table: public.daily_summary

-- DROP TABLE IF EXISTS public.daily_summary;

CREATE TABLE IF NOT EXISTS public.daily_summary
(
    summary_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    user_id integer NOT NULL,
    summary_date date NOT NULL,
    intake_calories integer DEFAULT 0,
    burned_calories integer DEFAULT 0,
    net_calories integer GENERATED ALWAYS AS ((intake_calories - burned_calories)) STORED,
    meals_count integer DEFAULT 0,
    exercise_duration_min integer DEFAULT 0,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT daily_summary_pkey PRIMARY KEY (summary_id),
    CONSTRAINT daily_summary_summary_date_key UNIQUE (summary_date)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.daily_summary
    OWNER to postgres;
