PGDMP     )    	                 t            ecsdb    9.3.6    9.3.6 "    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            �           1262    95052    ecsdb    DATABASE     w   CREATE DATABASE ecsdb WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'de_DE.UTF-8' LC_CTYPE = 'de_DE.UTF-8';
    DROP DATABASE ecsdb;
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            �           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    5            �           0    0    public    ACL     �   REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;
                  postgres    false    5            �            3079    11757    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            �           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    177            �            1259    101481    authorizationcodeentity    TABLE       CREATE TABLE authorizationcodeentity (
    id character varying(255) NOT NULL,
    creation_time timestamp without time zone NOT NULL,
    modification_time timestamp without time zone,
    valid_until_time timestamp without time zone NOT NULL,
    version bigint
);
 +   DROP TABLE public.authorizationcodeentity;
       public         ecs    false    5            �            1259    101453    devices    TABLE     p  CREATE TABLE devices (
    id character varying(255) NOT NULL,
    creation_time timestamp without time zone NOT NULL,
    maker character varying(255),
    model character varying(255),
    modification_time timestamp without time zone,
    software character varying(255),
    source character varying(255),
    version bigint,
    user_id character varying(255)
);
    DROP TABLE public.devices;
       public         ecs    false    5            �            1259    101461 	   positions    TABLE       CREATE TABLE positions (
    id bigint NOT NULL,
    average_altitude double precision,
    creation_time timestamp without time zone NOT NULL,
    latitude double precision,
    longitude double precision,
    modification_time timestamp without time zone,
    version bigint
);
    DROP TABLE public.positions;
       public         ecs    false    5            �            1259    101466    positions_in_time    TABLE     �  CREATE TABLE positions_in_time (
    id bigint NOT NULL,
    altitude double precision,
    creation_time timestamp without time zone NOT NULL,
    direction double precision,
    floor integer,
    horizontal_accuracy double precision,
    modification_time timestamp without time zone,
    speed double precision,
    trackedon timestamp without time zone,
    version bigint,
    vertical_accuracy double precision,
    position_id bigint,
    trip_id character varying(255)
);
 %   DROP TABLE public.positions_in_time;
       public         ecs    false    5            �            1259    95427    sequence    TABLE     d   CREATE TABLE sequence (
    seq_name character varying(50) NOT NULL,
    seq_count numeric(38,0)
);
    DROP TABLE public.sequence;
       public         ecs    false    5            �            1259    101445    trips    TABLE     n  CREATE TABLE trips (
    id character varying(255) NOT NULL,
    creation_time timestamp without time zone NOT NULL,
    modification_time timestamp without time zone,
    startedon timestamp without time zone NOT NULL,
    stoppedon timestamp without time zone NOT NULL,
    title character varying(255),
    version bigint,
    device_id character varying(255)
);
    DROP TABLE public.trips;
       public         ecs    false    5            �            1259    101471    users    TABLE     �  CREATE TABLE users (
    id character varying(255) NOT NULL,
    creation_time timestamp without time zone NOT NULL,
    email character varying(100) NOT NULL,
    first_name character varying(100) NOT NULL,
    last_name character varying(100) NOT NULL,
    modification_time timestamp without time zone,
    password character varying(255),
    role character varying(20) NOT NULL,
    version bigint
);
    DROP TABLE public.users;
       public         ecs    false    5            �          0    101481    authorizationcodeentity 
   TABLE DATA               k   COPY authorizationcodeentity (id, creation_time, modification_time, valid_until_time, version) FROM stdin;
    public       ecs    false    176            �          0    101453    devices 
   TABLE DATA               r   COPY devices (id, creation_time, maker, model, modification_time, software, source, version, user_id) FROM stdin;
    public       ecs    false    172            �          0    101461 	   positions 
   TABLE DATA               r   COPY positions (id, average_altitude, creation_time, latitude, longitude, modification_time, version) FROM stdin;
    public       ecs    false    173            �          0    101466    positions_in_time 
   TABLE DATA               �   COPY positions_in_time (id, altitude, creation_time, direction, floor, horizontal_accuracy, modification_time, speed, trackedon, version, vertical_accuracy, position_id, trip_id) FROM stdin;
    public       ecs    false    174            �          0    95427    sequence 
   TABLE DATA               0   COPY sequence (seq_name, seq_count) FROM stdin;
    public       ecs    false    170            �          0    101445    trips 
   TABLE DATA               o   COPY trips (id, creation_time, modification_time, startedon, stoppedon, title, version, device_id) FROM stdin;
    public       ecs    false    171            �          0    101471    users 
   TABLE DATA               u   COPY users (id, creation_time, email, first_name, last_name, modification_time, password, role, version) FROM stdin;
    public       ecs    false    175            M           2606    101485    authorizationcodeentity_pkey 
   CONSTRAINT     k   ALTER TABLE ONLY authorizationcodeentity
    ADD CONSTRAINT authorizationcodeentity_pkey PRIMARY KEY (id);
 ^   ALTER TABLE ONLY public.authorizationcodeentity DROP CONSTRAINT authorizationcodeentity_pkey;
       public         ecs    false    176    176            C           2606    101460    devices_pkey 
   CONSTRAINT     K   ALTER TABLE ONLY devices
    ADD CONSTRAINT devices_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.devices DROP CONSTRAINT devices_pkey;
       public         ecs    false    172    172            G           2606    101470    positions_in_time_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY positions_in_time
    ADD CONSTRAINT positions_in_time_pkey PRIMARY KEY (id);
 R   ALTER TABLE ONLY public.positions_in_time DROP CONSTRAINT positions_in_time_pkey;
       public         ecs    false    174    174            E           2606    101465    positions_pkey 
   CONSTRAINT     O   ALTER TABLE ONLY positions
    ADD CONSTRAINT positions_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.positions DROP CONSTRAINT positions_pkey;
       public         ecs    false    173    173            ?           2606    95431    sequence_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY sequence
    ADD CONSTRAINT sequence_pkey PRIMARY KEY (seq_name);
 @   ALTER TABLE ONLY public.sequence DROP CONSTRAINT sequence_pkey;
       public         ecs    false    170    170            A           2606    101452 
   trips_pkey 
   CONSTRAINT     G   ALTER TABLE ONLY trips
    ADD CONSTRAINT trips_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.trips DROP CONSTRAINT trips_pkey;
       public         ecs    false    171    171            I           2606    101480    users_email_key 
   CONSTRAINT     J   ALTER TABLE ONLY users
    ADD CONSTRAINT users_email_key UNIQUE (email);
 ?   ALTER TABLE ONLY public.users DROP CONSTRAINT users_email_key;
       public         ecs    false    175    175            K           2606    101478 
   users_pkey 
   CONSTRAINT     G   ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public         ecs    false    175    175            O           2606    101491    fk_devices_user_id    FK CONSTRAINT     k   ALTER TABLE ONLY devices
    ADD CONSTRAINT fk_devices_user_id FOREIGN KEY (user_id) REFERENCES users(id);
 D   ALTER TABLE ONLY public.devices DROP CONSTRAINT fk_devices_user_id;
       public       ecs    false    172    1867    175            P           2606    101496     fk_positions_in_time_position_id    FK CONSTRAINT     �   ALTER TABLE ONLY positions_in_time
    ADD CONSTRAINT fk_positions_in_time_position_id FOREIGN KEY (position_id) REFERENCES positions(id);
 \   ALTER TABLE ONLY public.positions_in_time DROP CONSTRAINT fk_positions_in_time_position_id;
       public       ecs    false    174    1861    173            Q           2606    101501    fk_positions_in_time_trip_id    FK CONSTRAINT        ALTER TABLE ONLY positions_in_time
    ADD CONSTRAINT fk_positions_in_time_trip_id FOREIGN KEY (trip_id) REFERENCES trips(id);
 X   ALTER TABLE ONLY public.positions_in_time DROP CONSTRAINT fk_positions_in_time_trip_id;
       public       ecs    false    171    1857    174            N           2606    101486    fk_trips_device_id    FK CONSTRAINT     m   ALTER TABLE ONLY trips
    ADD CONSTRAINT fk_trips_device_id FOREIGN KEY (device_id) REFERENCES devices(id);
 B   ALTER TABLE ONLY public.trips DROP CONSTRAINT fk_trips_device_id;
       public       ecs    false    1859    172    171            �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x�v�ww��4������ T      �      x������ � �      �      x������ � �     