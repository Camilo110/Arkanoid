PGDMP             
            y            postgres    13.2    13.2     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    13442    postgres    DATABASE     g   CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Spanish_Colombia.1252';
    DROP DATABASE postgres;
                postgres    false            �           0    0    DATABASE postgres    COMMENT     N   COMMENT ON DATABASE postgres IS 'default administrative connection database';
                   postgres    false    2996            �            1259    16487    Puntajes    TABLE     a   CREATE TABLE public."Puntajes" (
    nombre character varying,
    puntajes character varying
);
    DROP TABLE public."Puntajes";
       public         heap    postgres    false            �          0    16487    Puntajes 
   TABLE DATA           6   COPY public."Puntajes" (nombre, puntajes) FROM stdin;
    public          postgres    false    204   �       �   �   x���N�@���>��L�b2��`H�Tuq��f.�0Tҧw(��ֵ��7_�e�AI��K��
IcL��Q�I��s(Q����|<�%g ��
�{<���	Kck��5�N�Wq|�c�` ի':�`�����	kl��.���@��>����d�=R�ir��W3W��6�#G���&Y>z�F����fr|׃�[:'6�/���Œ�:����u�nį��{�O�ނ�1>�K�?!|��0���T     