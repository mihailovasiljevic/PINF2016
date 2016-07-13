/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2012                    */
/* Created on:     13.07.2016. 11:27:50                         */
/*==============================================================*/


if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('ANALITIKA_MAGACINSKE_KARTICE') and o.name = 'FK_ANALITIK_ANALTIIKA_ROBNA_KA')
alter table ANALITIKA_MAGACINSKE_KARTICE
   drop constraint FK_ANALITIK_ANALTIIKA_ROBNA_KA
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('CLAN_KOMISIJE') and o.name = 'FK_CLAN_KOM_RADNIK_KA_RASPORED')
alter table CLAN_KOMISIJE
   drop constraint FK_CLAN_KOM_RADNIK_KA_RASPORED
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('CLAN_POPISNE_KOMISIJE') and o.name = 'FK_CLAN_POP_CLAN_POPI_POPISNA_')
alter table CLAN_POPISNE_KOMISIJE
   drop constraint FK_CLAN_POP_CLAN_POPI_POPISNA_
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('CLAN_POPISNE_KOMISIJE') and o.name = 'FK_CLAN_POP_CLAN_POPI_CLAN_KOM')
alter table CLAN_POPISNE_KOMISIJE
   drop constraint FK_CLAN_POP_CLAN_POPI_CLAN_KOM
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('FUNKCIJA') and o.name = 'FK_FUNKCIJA_FUNKCIJA__POSLOVNI')
alter table FUNKCIJA
   drop constraint FK_FUNKCIJA_FUNKCIJA__POSLOVNI
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('GRUPA_ROBA') and o.name = 'FK_GRUPA_RO_POSLOVNI__POSLOVNI')
alter table GRUPA_ROBA
   drop constraint FK_GRUPA_RO_POSLOVNI__POSLOVNI
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('MAGACIN') and o.name = 'FK_MAGACIN_MAGACIN_P_SEKTOR')
alter table MAGACIN
   drop constraint FK_MAGACIN_MAGACIN_P_SEKTOR
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('POPISNI_DOKUMENT') and o.name = 'FK_POPISNI__GODINA_PO_POSLOVNA')
alter table POPISNI_DOKUMENT
   drop constraint FK_POPISNI__GODINA_PO_POSLOVNA
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('POPISNI_DOKUMENT') and o.name = 'FK_POPISNI__NAZIV_ODE_MAGACIN')
alter table POPISNI_DOKUMENT
   drop constraint FK_POPISNI__NAZIV_ODE_MAGACIN
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('POPISNI_DOKUMENT') and o.name = 'FK_POPISNI__POSLOVNI__POSLOVNI')
alter table POPISNI_DOKUMENT
   drop constraint FK_POPISNI__POSLOVNI__POSLOVNI
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('POPISNI_DOKUMENT') and o.name = 'FK_POPISNI__PROPISNA__POPISNA_')
alter table POPISNI_DOKUMENT
   drop constraint FK_POPISNI__PROPISNA__POPISNA_
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('POSLOVNA_GODINA') and o.name = 'FK_POSLOVNA_POSLOVNA__POSLOVNI')
alter table POSLOVNA_GODINA
   drop constraint FK_POSLOVNA_POSLOVNA__POSLOVNI
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('POSLOVNI_PARTNER') and o.name = 'FK_POSLOVNI_PARTNER_POSLOVNI')
alter table POSLOVNI_PARTNER
   drop constraint FK_POSLOVNI_PARTNER_POSLOVNI
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('POSLOVNI_PARTNER') and o.name = 'FK_POSLOVNI_PREDUZECE_POSLOVNI')
alter table POSLOVNI_PARTNER
   drop constraint FK_POSLOVNI_PREDUZECE_POSLOVNI
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('POSLOVNI_SISTEM') and o.name = 'FK_POSLOVNI_SEDISTE_MESTO')
alter table POSLOVNI_SISTEM
   drop constraint FK_POSLOVNI_SEDISTE_MESTO
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('PROMETNI_DOKUMENT') and o.name = 'FK_PROMETNI_GODINA_PR_POSLOVNA')
alter table PROMETNI_DOKUMENT
   drop constraint FK_PROMETNI_GODINA_PR_POSLOVNA
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('PROMETNI_DOKUMENT') and o.name = 'FK_PROMETNI_LOKACIJA__MAGACIN')
alter table PROMETNI_DOKUMENT
   drop constraint FK_PROMETNI_LOKACIJA__MAGACIN
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('PROMETNI_DOKUMENT') and o.name = 'FK_PROMETNI_RELATIONS_MAGACIN')
alter table PROMETNI_DOKUMENT
   drop constraint FK_PROMETNI_RELATIONS_MAGACIN
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('PROMETNI_DOKUMENT') and o.name = 'FK_PROMETNI_STRANKA_U_POSLOVNI')
alter table PROMETNI_DOKUMENT
   drop constraint FK_PROMETNI_STRANKA_U_POSLOVNI
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('RADNIK') and o.name = 'FK_RADNIK_FUNKCIJA__FUNKCIJA')
alter table RADNIK
   drop constraint FK_RADNIK_FUNKCIJA__FUNKCIJA
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('RASPORED_NA_RADNA_MESTA') and o.name = 'FK_RASPORED_RASPOREDJ_RADNIK')
alter table RASPORED_NA_RADNA_MESTA
   drop constraint FK_RASPORED_RASPOREDJ_RADNIK
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('RASPORED_NA_RADNA_MESTA') and o.name = 'FK_RASPORED_RASPORED__MAGACIN')
alter table RASPORED_NA_RADNA_MESTA
   drop constraint FK_RASPORED_RASPORED__MAGACIN
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('ROBA') and o.name = 'FK_ROBA_JEDINICA__JEDINICA')
alter table ROBA
   drop constraint FK_ROBA_JEDINICA__JEDINICA
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('ROBA') and o.name = 'FK_ROBA_ROBA_PRIP_GRUPA_RO')
alter table ROBA
   drop constraint FK_ROBA_ROBA_PRIP_GRUPA_RO
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('ROBNA_KARTICA') and o.name = 'FK_ROBNA_KA_GODINA_RO_POSLOVNA')
alter table ROBNA_KARTICA
   drop constraint FK_ROBNA_KA_GODINA_RO_POSLOVNA
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('ROBNA_KARTICA') and o.name = 'FK_ROBNA_KA_RELATIONS_MAGACIN')
alter table ROBNA_KARTICA
   drop constraint FK_ROBNA_KA_RELATIONS_MAGACIN
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('ROBNA_KARTICA') and o.name = 'FK_ROBNA_KA_ROBA_CIJA_ROBA')
alter table ROBNA_KARTICA
   drop constraint FK_ROBNA_KA_ROBA_CIJA_ROBA
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('SEKTOR') and o.name = 'FK_SEKTOR_POSLOVNI__POSLOVNI')
alter table SEKTOR
   drop constraint FK_SEKTOR_POSLOVNI__POSLOVNI
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('STAVKA_POPISNOG_DOKUMENTA') and o.name = 'FK_STAVKA_P_POPISNI_D_POPISNI_')
alter table STAVKA_POPISNOG_DOKUMENTA
   drop constraint FK_STAVKA_P_POPISNI_D_POPISNI_
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('STAVKA_POPISNOG_DOKUMENTA') and o.name = 'FK_ST_P_RA_U_ST_ROBA')
alter table STAVKA_POPISNOG_DOKUMENTA
   drop constraint FK_ST_P_RA_U_ST_ROBA
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('STAVKA_PROMETNOG_DOKUMENTA') and o.name = 'FK_STA_P_U_ST_ROBA')
alter table STAVKA_PROMETNOG_DOKUMENTA
   drop constraint FK_STA_P_U_ST_ROBA
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('STAVKA_PROMETNOG_DOKUMENTA') and o.name = 'FK_STAVKA_P_STAVKA_PR_PROMETNI')
alter table STAVKA_PROMETNOG_DOKUMENTA
   drop constraint FK_STAVKA_P_STAVKA_PR_PROMETNI
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('ANALITIKA_MAGACINSKE_KARTICE')
            and   name  = 'ANALTIIKA_ROBE_FK'
            and   indid > 0
            and   indid < 255)
   drop index ANALITIKA_MAGACINSKE_KARTICE.ANALTIIKA_ROBE_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('ANALITIKA_MAGACINSKE_KARTICE')
            and   type = 'U')
   drop table ANALITIKA_MAGACINSKE_KARTICE
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('CLAN_KOMISIJE')
            and   name  = 'RADNIK_KAO_CLAN_KOMISIJE_FK'
            and   indid > 0
            and   indid < 255)
   drop index CLAN_KOMISIJE.RADNIK_KAO_CLAN_KOMISIJE_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('CLAN_KOMISIJE')
            and   type = 'U')
   drop table CLAN_KOMISIJE
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('CLAN_POPISNE_KOMISIJE')
            and   name  = 'CLAN_POPISNE_KOMISIJE2_FK'
            and   indid > 0
            and   indid < 255)
   drop index CLAN_POPISNE_KOMISIJE.CLAN_POPISNE_KOMISIJE2_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('CLAN_POPISNE_KOMISIJE')
            and   name  = 'CLAN_POPISNE_KOMISIJE_FK'
            and   indid > 0
            and   indid < 255)
   drop index CLAN_POPISNE_KOMISIJE.CLAN_POPISNE_KOMISIJE_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('CLAN_POPISNE_KOMISIJE')
            and   type = 'U')
   drop table CLAN_POPISNE_KOMISIJE
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('FUNKCIJA')
            and   name  = 'FUNKCIJA_U_OKVIRU_POSLOVNOG_SISTEMA_FK'
            and   indid > 0
            and   indid < 255)
   drop index FUNKCIJA.FUNKCIJA_U_OKVIRU_POSLOVNOG_SISTEMA_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('FUNKCIJA')
            and   type = 'U')
   drop table FUNKCIJA
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('GRUPA_ROBA')
            and   name  = 'POSLOVNI_SISTEM_DEFINISE_GRUPE_FK'
            and   indid > 0
            and   indid < 255)
   drop index GRUPA_ROBA.POSLOVNI_SISTEM_DEFINISE_GRUPE_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('GRUPA_ROBA')
            and   type = 'U')
   drop table GRUPA_ROBA
go

if exists (select 1
            from  sysobjects
           where  id = object_id('JEDINICA_MERE')
            and   type = 'U')
   drop table JEDINICA_MERE
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('MAGACIN')
            and   name  = 'MAGACIN_PRIPADA_SEKTORU_FK'
            and   indid > 0
            and   indid < 255)
   drop index MAGACIN.MAGACIN_PRIPADA_SEKTORU_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('MAGACIN')
            and   type = 'U')
   drop table MAGACIN
go

if exists (select 1
            from  sysobjects
           where  id = object_id('MESTO')
            and   type = 'U')
   drop table MESTO
go

if exists (select 1
            from  sysobjects
           where  id = object_id('POPISNA_KOMISIJA')
            and   type = 'U')
   drop table POPISNA_KOMISIJA
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('POPISNI_DOKUMENT')
            and   name  = 'GODINA_POPISA_FK'
            and   indid > 0
            and   indid < 255)
   drop index POPISNI_DOKUMENT.GODINA_POPISA_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('POPISNI_DOKUMENT')
            and   name  = 'POSLOVNI_SISTEM_FK'
            and   indid > 0
            and   indid < 255)
   drop index POPISNI_DOKUMENT.POSLOVNI_SISTEM_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('POPISNI_DOKUMENT')
            and   name  = 'NAZIV_ODELJENJA_FK'
            and   indid > 0
            and   indid < 255)
   drop index POPISNI_DOKUMENT.NAZIV_ODELJENJA_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('POPISNI_DOKUMENT')
            and   name  = 'PROPISNA_KOMISIJA_BRINE_O_PROPISNOM_DOKUMENTU_FK'
            and   indid > 0
            and   indid < 255)
   drop index POPISNI_DOKUMENT.PROPISNA_KOMISIJA_BRINE_O_PROPISNOM_DOKUMENTU_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('POPISNI_DOKUMENT')
            and   type = 'U')
   drop table POPISNI_DOKUMENT
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('POSLOVNA_GODINA')
            and   name  = 'POSLOVNA_GODINA_POSLOVNOG_SISTEMA_FK'
            and   indid > 0
            and   indid < 255)
   drop index POSLOVNA_GODINA.POSLOVNA_GODINA_POSLOVNOG_SISTEMA_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('POSLOVNA_GODINA')
            and   type = 'U')
   drop table POSLOVNA_GODINA
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('POSLOVNI_PARTNER')
            and   name  = 'PARTNER_FK'
            and   indid > 0
            and   indid < 255)
   drop index POSLOVNI_PARTNER.PARTNER_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('POSLOVNI_PARTNER')
            and   name  = 'PREDUZECE_FK'
            and   indid > 0
            and   indid < 255)
   drop index POSLOVNI_PARTNER.PREDUZECE_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('POSLOVNI_PARTNER')
            and   type = 'U')
   drop table POSLOVNI_PARTNER
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('POSLOVNI_SISTEM')
            and   name  = 'SEDISTE_FK'
            and   indid > 0
            and   indid < 255)
   drop index POSLOVNI_SISTEM.SEDISTE_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('POSLOVNI_SISTEM')
            and   type = 'U')
   drop table POSLOVNI_SISTEM
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('PROMETNI_DOKUMENT')
            and   name  = 'RELATIONSHIP_30_FK'
            and   indid > 0
            and   indid < 255)
   drop index PROMETNI_DOKUMENT.RELATIONSHIP_30_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('PROMETNI_DOKUMENT')
            and   name  = 'LOKACIJA_U_PROMETU_FK'
            and   indid > 0
            and   indid < 255)
   drop index PROMETNI_DOKUMENT.LOKACIJA_U_PROMETU_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('PROMETNI_DOKUMENT')
            and   name  = 'STRANKA_U_PROMETU_FK'
            and   indid > 0
            and   indid < 255)
   drop index PROMETNI_DOKUMENT.STRANKA_U_PROMETU_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('PROMETNI_DOKUMENT')
            and   name  = 'GODINA_PROMETNOG_DOKUMENTA_FK'
            and   indid > 0
            and   indid < 255)
   drop index PROMETNI_DOKUMENT.GODINA_PROMETNOG_DOKUMENTA_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('PROMETNI_DOKUMENT')
            and   type = 'U')
   drop table PROMETNI_DOKUMENT
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('RADNIK')
            and   name  = 'FUNKCIJA_RADNIKA_FK'
            and   indid > 0
            and   indid < 255)
   drop index RADNIK.FUNKCIJA_RADNIKA_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('RADNIK')
            and   type = 'U')
   drop table RADNIK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('RASPORED_NA_RADNA_MESTA')
            and   name  = 'RASPOREDJENI_RADNIK_FK'
            and   indid > 0
            and   indid < 255)
   drop index RASPORED_NA_RADNA_MESTA.RASPOREDJENI_RADNIK_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('RASPORED_NA_RADNA_MESTA')
            and   name  = 'RASPORED_NA_RADNA_MESTA_ORGANIZACIONE_JEDINICE_FK'
            and   indid > 0
            and   indid < 255)
   drop index RASPORED_NA_RADNA_MESTA.RASPORED_NA_RADNA_MESTA_ORGANIZACIONE_JEDINICE_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('RASPORED_NA_RADNA_MESTA')
            and   type = 'U')
   drop table RASPORED_NA_RADNA_MESTA
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('ROBA')
            and   name  = 'ROBA_PRIPADA_GRUPI_FK'
            and   indid > 0
            and   indid < 255)
   drop index ROBA.ROBA_PRIPADA_GRUPI_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('ROBA')
            and   name  = 'JEDINICA_MERE_ROBE_FK'
            and   indid > 0
            and   indid < 255)
   drop index ROBA.JEDINICA_MERE_ROBE_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('ROBA')
            and   type = 'U')
   drop table ROBA
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('ROBNA_KARTICA')
            and   name  = 'SADRZI_FK'
            and   indid > 0
            and   indid < 255)
   drop index ROBNA_KARTICA.SADRZI_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('ROBNA_KARTICA')
            and   name  = 'ROBA_CIJA_JE_ROBNA_KARTICA_FK'
            and   indid > 0
            and   indid < 255)
   drop index ROBNA_KARTICA.ROBA_CIJA_JE_ROBNA_KARTICA_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('ROBNA_KARTICA')
            and   name  = 'GODINA_ROBNE_KARTICE_FK'
            and   indid > 0
            and   indid < 255)
   drop index ROBNA_KARTICA.GODINA_ROBNE_KARTICE_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('ROBNA_KARTICA')
            and   type = 'U')
   drop table ROBNA_KARTICA
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('SEKTOR')
            and   name  = 'POSLOVNI_SISTEM_SE_SASTOJI_OD_SEKTORA_FK'
            and   indid > 0
            and   indid < 255)
   drop index SEKTOR.POSLOVNI_SISTEM_SE_SASTOJI_OD_SEKTORA_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SEKTOR')
            and   type = 'U')
   drop table SEKTOR
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('STAVKA_POPISNOG_DOKUMENTA')
            and   name  = 'POPISNI_DOKUMENT_SE_SASTOJI_OD_STAVKI_FK'
            and   indid > 0
            and   indid < 255)
   drop index STAVKA_POPISNOG_DOKUMENTA.POPISNI_DOKUMENT_SE_SASTOJI_OD_STAVKI_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('STAVKA_POPISNOG_DOKUMENTA')
            and   name  = 'ROBA_U_STAVCI_POPISA_FK'
            and   indid > 0
            and   indid < 255)
   drop index STAVKA_POPISNOG_DOKUMENTA.ROBA_U_STAVCI_POPISA_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('STAVKA_POPISNOG_DOKUMENTA')
            and   type = 'U')
   drop table STAVKA_POPISNOG_DOKUMENTA
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('STAVKA_PROMETNOG_DOKUMENTA')
            and   name  = 'STAVKA_PROMETNOG_DOKUMENTA_FK'
            and   indid > 0
            and   indid < 255)
   drop index STAVKA_PROMETNOG_DOKUMENTA.STAVKA_PROMETNOG_DOKUMENTA_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('STAVKA_PROMETNOG_DOKUMENTA')
            and   name  = 'ROBA_U_STAVCI_DOKUMENTA_FK'
            and   indid > 0
            and   indid < 255)
   drop index STAVKA_PROMETNOG_DOKUMENTA.ROBA_U_STAVCI_DOKUMENTA_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('STAVKA_PROMETNOG_DOKUMENTA')
            and   type = 'U')
   drop table STAVKA_PROMETNOG_DOKUMENTA
go

/*==============================================================*/
/* Table: ANALITIKA_MAGACINSKE_KARTICE                          */
/*==============================================================*/
create table ANALITIKA_MAGACINSKE_KARTICE (
   ANALTIKA_ID          int                  identity,
   ROB_KART_ID          int                  not null,
   ANALTIKA_RBR         int                  not null,
   ANALITIKA_SMER       char(1)              not null
      constraint CKC_ANALITIKA_SMER_ANALITIK check (ANALITIKA_SMER in ('U','I')),
   ANALTIKA_KOL         numeric(14,4)        not null,
   ANALITIKA_CENA       numeric(14,4)        not null,
   ANALITIKA_VRED       numeric(14,4)        not null,
   ANALITIKA_VRS_PROM   char(1)              null
      constraint CKC_ANALITIKA_VRS_PRO_ANALITIK check (ANALITIKA_VRS_PROM is null or (ANALITIKA_VRS_PROM in ('P','O','M','N','S','K'))),
   constraint PK_ANALITIKA_MAGACINSKE_KARTIC primary key nonclustered (ANALTIKA_ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('ANALITIKA_MAGACINSKE_KARTICE') and minor_id = 0)
begin 
   declare @CurrentUser sysname 
select @CurrentUser = user_name() 
execute sp_dropextendedproperty 'MS_Description',  
   'user', @CurrentUser, 'table', 'ANALITIKA_MAGACINSKE_KARTICE' 
 
end 


select @CurrentUser = user_name() 
execute sp_addextendedproperty 'MS_Description',  
   'Analtika robne kartice.', 
   'user', @CurrentUser, 'table', 'ANALITIKA_MAGACINSKE_KARTICE'
go

/*==============================================================*/
/* Index: ANALTIIKA_ROBE_FK                                     */
/*==============================================================*/
create index ANALTIIKA_ROBE_FK on ANALITIKA_MAGACINSKE_KARTICE (
ROB_KART_ID ASC
)
go

/*==============================================================*/
/* Table: CLAN_KOMISIJE                                         */
/*==============================================================*/
create table CLAN_KOMISIJE (
   CLAN_ID              int                  identity,
   RASPORED_ID          int                  not null,
   CLAN_VRSTA           bit                  null
      constraint CKC_CLAN_VRSTA_CLAN_KOM check (CLAN_VRSTA is null or (CLAN_VRSTA in (0,1))),
   constraint PK_CLAN_KOMISIJE primary key nonclustered (CLAN_ID)
)
go

/*==============================================================*/
/* Index: RADNIK_KAO_CLAN_KOMISIJE_FK                           */
/*==============================================================*/
create index RADNIK_KAO_CLAN_KOMISIJE_FK on CLAN_KOMISIJE (
RASPORED_ID ASC
)
go

/*==============================================================*/
/* Table: CLAN_POPISNE_KOMISIJE                                 */
/*==============================================================*/
create table CLAN_POPISNE_KOMISIJE (
   POP_KOM_ID           int                  identity,
   CLAN_ID              int                  not null,
   constraint PK_CLAN_POPISNE_KOMISIJE primary key (POP_KOM_ID, CLAN_ID)
)
go

/*==============================================================*/
/* Index: CLAN_POPISNE_KOMISIJE_FK                              */
/*==============================================================*/
create index CLAN_POPISNE_KOMISIJE_FK on CLAN_POPISNE_KOMISIJE (
POP_KOM_ID ASC
)
go

/*==============================================================*/
/* Index: CLAN_POPISNE_KOMISIJE2_FK                             */
/*==============================================================*/
create index CLAN_POPISNE_KOMISIJE2_FK on CLAN_POPISNE_KOMISIJE (
CLAN_ID ASC
)
go

/*==============================================================*/
/* Table: FUNKCIJA                                              */
/*==============================================================*/
create table FUNKCIJA (
   FUNKCIJA_ID          int                  identity,
   POSL_SIS_ID          int                  not null,
   FUNKCIJA_NAZ         varchar(80)          not null,
   constraint PK_FUNKCIJA primary key nonclustered (FUNKCIJA_ID)
)
go

/*==============================================================*/
/* Index: FUNKCIJA_U_OKVIRU_POSLOVNOG_SISTEMA_FK                */
/*==============================================================*/
create index FUNKCIJA_U_OKVIRU_POSLOVNOG_SISTEMA_FK on FUNKCIJA (
POSL_SIS_ID ASC
)
go

/*==============================================================*/
/* Table: GRUPA_ROBA                                            */
/*==============================================================*/
create table GRUPA_ROBA (
   GRUPA_ID             int                  identity,
   POSL_SIS_ID          int                  not null,
   GRUPA_NAZ            varchar(80)          not null,
   constraint PK_GRUPA_ROBA primary key nonclustered (GRUPA_ID)
)
go

/*==============================================================*/
/* Index: POSLOVNI_SISTEM_DEFINISE_GRUPE_FK                     */
/*==============================================================*/
create index POSLOVNI_SISTEM_DEFINISE_GRUPE_FK on GRUPA_ROBA (
POSL_SIS_ID ASC
)
go

/*==============================================================*/
/* Table: JEDINICA_MERE                                         */
/*==============================================================*/
create table JEDINICA_MERE (
   JEDINICA_ID          int                  identity,
   JEDINICA_NAZ         varchar(80)          not null,
   constraint PK_JEDINICA_MERE primary key nonclustered (JEDINICA_ID)
)
go

/*==============================================================*/
/* Table: MAGACIN                                               */
/*==============================================================*/
create table MAGACIN (
   MAG_ID               int                  identity,
   SEKT_ID              int                  not null,
   MAG_NAZ              varchar(120)         not null,
   constraint PK_MAGACIN primary key nonclustered (MAG_ID)
)
go

/*==============================================================*/
/* Index: MAGACIN_PRIPADA_SEKTORU_FK                            */
/*==============================================================*/
create index MAGACIN_PRIPADA_SEKTORU_FK on MAGACIN (
SEKT_ID ASC
)
go

/*==============================================================*/
/* Table: MESTO                                                 */
/*==============================================================*/
create table MESTO (
   MESTO_ID             int                  identity,
   MESTO_NAZ            varchar(60)          not null,
   MESTO_PTT            varchar(20)          null,
   constraint PK_MESTO primary key nonclustered (MESTO_ID)
)
go

/*==============================================================*/
/* Table: POPISNA_KOMISIJA                                      */
/*==============================================================*/
create table POPISNA_KOMISIJA (
   POP_KOM_ID           int                  identity,
   POP_KOM_OBAV         bit                  null,
   constraint PK_POPISNA_KOMISIJA primary key nonclustered (POP_KOM_ID)
)
go

/*==============================================================*/
/* Table: POPISNI_DOKUMENT                                      */
/*==============================================================*/
create table POPISNI_DOKUMENT (
   POP_DOK_ID           int                  identity,
   MAG_ID               int                  not null,
   POSL_SIS_ID          int                  not null,
   POP_KOM_ID           int                  not null,
   POSL_GOD_ID          int                  not null,
   POP_DOK_DAT          datetime             not null,
   constraint PK_POPISNI_DOKUMENT primary key nonclustered (POP_DOK_ID)
)
go

/*==============================================================*/
/* Index: PROPISNA_KOMISIJA_BRINE_O_PROPISNOM_DOKUMENTU_FK      */
/*==============================================================*/
create index PROPISNA_KOMISIJA_BRINE_O_PROPISNOM_DOKUMENTU_FK on POPISNI_DOKUMENT (
POP_KOM_ID ASC
)
go

/*==============================================================*/
/* Index: NAZIV_ODELJENJA_FK                                    */
/*==============================================================*/
create index NAZIV_ODELJENJA_FK on POPISNI_DOKUMENT (
MAG_ID ASC
)
go

/*==============================================================*/
/* Index: POSLOVNI_SISTEM_FK                                    */
/*==============================================================*/
create index POSLOVNI_SISTEM_FK on POPISNI_DOKUMENT (
POSL_SIS_ID ASC
)
go

/*==============================================================*/
/* Index: GODINA_POPISA_FK                                      */
/*==============================================================*/
create index GODINA_POPISA_FK on POPISNI_DOKUMENT (
POSL_GOD_ID ASC
)
go

/*==============================================================*/
/* Table: POSLOVNA_GODINA                                       */
/*==============================================================*/
create table POSLOVNA_GODINA (
   POSL_GOD_ID          int                  identity,
   POSL_SIS_ID          int                  not null,
   POSL_GOD_GOD         char(4)              not null,
   POSL_GOD_DAT_ID      datetime             not null,
   POSL_GOD_DAT_DO      datetime             null,
   POSL_GOD_ZAKLJ       bit                  not null,
   constraint PK_POSLOVNA_GODINA primary key nonclustered (POSL_GOD_ID)
)
go

/*==============================================================*/
/* Index: POSLOVNA_GODINA_POSLOVNOG_SISTEMA_FK                  */
/*==============================================================*/
create index POSLOVNA_GODINA_POSLOVNOG_SISTEMA_FK on POSLOVNA_GODINA (
POSL_SIS_ID ASC
)
go

/*==============================================================*/
/* Table: POSLOVNI_PARTNER                                      */
/*==============================================================*/
create table POSLOVNI_PARTNER (
   POS_POSL_SIS_ID      int                  identity,
   POSL_SIS_ID          int                  not null,
   constraint PK_POSLOVNI_PARTNER primary key (POS_POSL_SIS_ID, POSL_SIS_ID)
)
go

/*==============================================================*/
/* Index: PREDUZECE_FK                                          */
/*==============================================================*/
create index PREDUZECE_FK on POSLOVNI_PARTNER (
POS_POSL_SIS_ID ASC
)
go

/*==============================================================*/
/* Index: PARTNER_FK                                            */
/*==============================================================*/
create index PARTNER_FK on POSLOVNI_PARTNER (
POSL_SIS_ID ASC
)
go

/*==============================================================*/
/* Table: POSLOVNI_SISTEM                                       */
/*==============================================================*/
create table POSLOVNI_SISTEM (
   POSL_SIS_ID          int                  identity,
   MESTO_ID             int                  not null,
   POSL_SIS_NAZ         varchar(100)         not null,
   POSL_SIS_PIB         char(10)             not null,
   POSL_SIS_MBR         char(8)              not null,
   POSL_SIS_ADR         varchar(80)          null,
   POSL_SIS_WEB         varchar(80)          null,
   POSL_SIS_TEL         varchar(32)          null,
   POSL_SIS_EMAIL       varchar(60)          null,
   POSL_SIS_TIP_PART    smallint             null,
   constraint PK_POSLOVNI_SISTEM primary key nonclustered (POSL_SIS_ID)
)
go

/*==============================================================*/
/* Index: SEDISTE_FK                                            */
/*==============================================================*/
create index SEDISTE_FK on POSLOVNI_SISTEM (
MESTO_ID ASC
)
go

/*==============================================================*/
/* Table: PROMETNI_DOKUMENT                                     */
/*==============================================================*/
create table PROMETNI_DOKUMENT (
   PROM_DOK_ID          int                  identity,
   POSL_SIS_ID          int                  not null,
   MAG_ID               int                  null,
   MAG_MAG_ID           int                  null,
   POSL_GOD_ID          int                  not null,
   PROM_DOK_NAZ         varchar(80)          not null,
   PROM_DOK_RBR         int                  not null,
   PROM_DOK_DAT_FORM    datetime             not null,
   PROM_DOK_DAT_KNJIZ   datetime             null,
   PROM_DOK_STAT        char(1)              not null
      constraint CKC_PROM_DOK_STAT_PROMETNI check (PROM_DOK_STAT in ('F','P','S')),
   PROM_DOK_VRST        char(1)              not null
      constraint CKC_PROM_DOK_VRST_PROMETNI check (PROM_DOK_VRST in ('P','O','M')),
   constraint PK_PROMETNI_DOKUMENT primary key nonclustered (PROM_DOK_ID)
)
go

/*==============================================================*/
/* Index: GODINA_PROMETNOG_DOKUMENTA_FK                         */
/*==============================================================*/
create index GODINA_PROMETNOG_DOKUMENTA_FK on PROMETNI_DOKUMENT (
POSL_GOD_ID ASC
)
go

/*==============================================================*/
/* Index: STRANKA_U_PROMETU_FK                                  */
/*==============================================================*/
create index STRANKA_U_PROMETU_FK on PROMETNI_DOKUMENT (
POSL_SIS_ID ASC
)
go

/*==============================================================*/
/* Index: LOKACIJA_U_PROMETU_FK                                 */
/*==============================================================*/
create index LOKACIJA_U_PROMETU_FK on PROMETNI_DOKUMENT (
MAG_ID ASC
)
go

/*==============================================================*/
/* Index: RELATIONSHIP_30_FK                                    */
/*==============================================================*/
create index RELATIONSHIP_30_FK on PROMETNI_DOKUMENT (
MAG_MAG_ID ASC
)
go

/*==============================================================*/
/* Table: RADNIK                                                */
/*==============================================================*/
create table RADNIK (
   RADNIK_ID            int                  identity,
   FUNKCIJA_ID          int                  not null,
   RADNIK_RBR           int                  not null,
   RADNIK_IME           varchar(120)         not null,
   RADNIK_PREZ          varchar(120)         not null,
   JMBG                 char(13)             null,
   constraint PK_RADNIK primary key nonclustered (RADNIK_ID)
)
go

/*==============================================================*/
/* Index: FUNKCIJA_RADNIKA_FK                                   */
/*==============================================================*/
create index FUNKCIJA_RADNIKA_FK on RADNIK (
FUNKCIJA_ID ASC
)
go

/*==============================================================*/
/* Table: RASPORED_NA_RADNA_MESTA                               */
/*==============================================================*/
create table RASPORED_NA_RADNA_MESTA (
   RASPORED_ID          int                  identity,
   RADNIK_ID            int                  not null,
   MAG_ID               int                  not null,
   RASPORED_RBR         int                  not null,
   RASPORED_OD          datetime             not null,
   RASPORED_DO          datetime             null,
   constraint PK_RASPORED_NA_RADNA_MESTA primary key nonclustered (RASPORED_ID)
)
go

/*==============================================================*/
/* Index: RASPORED_NA_RADNA_MESTA_ORGANIZACIONE_JEDINICE_FK     */
/*==============================================================*/
create index RASPORED_NA_RADNA_MESTA_ORGANIZACIONE_JEDINICE_FK on RASPORED_NA_RADNA_MESTA (
MAG_ID ASC
)
go

/*==============================================================*/
/* Index: RASPOREDJENI_RADNIK_FK                                */
/*==============================================================*/
create index RASPOREDJENI_RADNIK_FK on RASPORED_NA_RADNA_MESTA (
RADNIK_ID ASC
)
go

/*==============================================================*/
/* Table: ROBA                                                  */
/*==============================================================*/
create table ROBA (
   ROBA_ID              int                  identity,
   JEDINICA_ID          int                  not null,
   GRUPA_ID             int                  not null,
   ROBA_NAZ             varchar(80)          not null,
   ROB_PAK              decimal(10,2)        not null,
   constraint PK_ROBA primary key nonclustered (ROBA_ID)
)
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('ROBA')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ROB_PAK')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'ROBA', 'column', 'ROB_PAK'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'U kojoj kolicini jedinice mere je roba prisutna. Na primer: 0.5 Tona( Jedinica mere) secera.',
   'user', @CurrentUser, 'table', 'ROBA', 'column', 'ROB_PAK'
go

/*==============================================================*/
/* Index: JEDINICA_MERE_ROBE_FK                                 */
/*==============================================================*/
create index JEDINICA_MERE_ROBE_FK on ROBA (
JEDINICA_ID ASC
)
go

/*==============================================================*/
/* Index: ROBA_PRIPADA_GRUPI_FK                                 */
/*==============================================================*/
create index ROBA_PRIPADA_GRUPI_FK on ROBA (
GRUPA_ID ASC
)
go

/*==============================================================*/
/* Table: ROBNA_KARTICA                                         */
/*==============================================================*/
create table ROBNA_KARTICA (
   ROB_KART_ID          int                  identity,
   MAG_ID               int                  not null,
   ROBA_ID              int                  not null,
   POSL_GOD_ID          int                  not null,
   ROB_KART_POC_KOL     numeric(14,4)        null,
   ROB_KART_POC_VRED    numeric(14,4)        null,
   ROB_KART_PRUL_KOL    numeric(14,4)        null,
   ROB_KART_PRUL_VRED   numeric(14,4)        null,
   ROB_KART_PRIZ_KOL    numeric(14,4)        null,
   ROB_KART_PRIZ_VRED   numeric(14,4)        null,
   ROB_KART_UKKOL       numeric(14,4)        null,
   ROB_KART_UKVRED      numeric(14,4)        null,
   ROB_KART_CEBA        numeric(14,4)        null,
   constraint PK_ROBNA_KARTICA primary key nonclustered (ROB_KART_ID)
)
go

/*==============================================================*/
/* Index: GODINA_ROBNE_KARTICE_FK                               */
/*==============================================================*/
create index GODINA_ROBNE_KARTICE_FK on ROBNA_KARTICA (
POSL_GOD_ID ASC
)
go

/*==============================================================*/
/* Index: ROBA_CIJA_JE_ROBNA_KARTICA_FK                         */
/*==============================================================*/
create index ROBA_CIJA_JE_ROBNA_KARTICA_FK on ROBNA_KARTICA (
ROBA_ID ASC
)
go

/*==============================================================*/
/* Index: SADRZI_FK                                             */
/*==============================================================*/
create index SADRZI_FK on ROBNA_KARTICA (
MAG_ID ASC
)
go

/*==============================================================*/
/* Table: SEKTOR                                                */
/*==============================================================*/
create table SEKTOR (
   SEKT_ID              int                  identity,
   POSL_SIS_ID          int                  not null,
   SEKT_NAZ             varchar(80)          null,
   constraint PK_SEKTOR primary key nonclustered (SEKT_ID)
)
go

/*==============================================================*/
/* Index: POSLOVNI_SISTEM_SE_SASTOJI_OD_SEKTORA_FK              */
/*==============================================================*/
create index POSLOVNI_SISTEM_SE_SASTOJI_OD_SEKTORA_FK on SEKTOR (
POSL_SIS_ID ASC
)
go

/*==============================================================*/
/* Table: STAVKA_POPISNOG_DOKUMENTA                             */
/*==============================================================*/
create table STAVKA_POPISNOG_DOKUMENTA (
   STAV_POP_DOK_ID      int                  identity,
   ROBA_ID              int                  not null,
   POP_DOK_ID           int                  not null,
   STAV_POP_DOK_KOL     numeric(14,4)        not null,
   constraint PK_STAVKA_POPISNOG_DOKUMENTA primary key nonclustered (STAV_POP_DOK_ID)
)
go

/*==============================================================*/
/* Index: ROBA_U_STAVCI_POPISA_FK                               */
/*==============================================================*/
create index ROBA_U_STAVCI_POPISA_FK on STAVKA_POPISNOG_DOKUMENTA (
ROBA_ID ASC
)
go

/*==============================================================*/
/* Index: POPISNI_DOKUMENT_SE_SASTOJI_OD_STAVKI_FK              */
/*==============================================================*/
create index POPISNI_DOKUMENT_SE_SASTOJI_OD_STAVKI_FK on STAVKA_POPISNOG_DOKUMENTA (
POP_DOK_ID ASC
)
go

/*==============================================================*/
/* Table: STAVKA_PROMETNOG_DOKUMENTA                            */
/*==============================================================*/
create table STAVKA_PROMETNOG_DOKUMENTA (
   STAV_PROM_DOK_ID     int                  identity,
   PROM_DOK_ID          int                  not null,
   ROBA_ID              int                  not null,
   STAV_PROM_DOK_KOL    numeric(14,4)        null,
   STAV_PROM_DOK_CEN    numeric(14,4)        null,
   STAV_PROM_DOK_VRED   numeric(14,4)        null,
   constraint PK_STAVKA_PROMETNOG_DOKUMENTA primary key nonclustered (STAV_PROM_DOK_ID)
)
go

/*==============================================================*/
/* Index: ROBA_U_STAVCI_DOKUMENTA_FK                            */
/*==============================================================*/
create index ROBA_U_STAVCI_DOKUMENTA_FK on STAVKA_PROMETNOG_DOKUMENTA (
ROBA_ID ASC
)
go

/*==============================================================*/
/* Index: STAVKA_PROMETNOG_DOKUMENTA_FK                         */
/*==============================================================*/
create index STAVKA_PROMETNOG_DOKUMENTA_FK on STAVKA_PROMETNOG_DOKUMENTA (
PROM_DOK_ID ASC
)
go

alter table ANALITIKA_MAGACINSKE_KARTICE
   add constraint FK_ANALITIK_ANALTIIKA_ROBNA_KA foreign key (ROB_KART_ID)
      references ROBNA_KARTICA (ROB_KART_ID)
go

alter table CLAN_KOMISIJE
   add constraint FK_CLAN_KOM_RADNIK_KA_RASPORED foreign key (RASPORED_ID)
      references RASPORED_NA_RADNA_MESTA (RASPORED_ID)
go

alter table CLAN_POPISNE_KOMISIJE
   add constraint FK_CLAN_POP_CLAN_POPI_POPISNA_ foreign key (POP_KOM_ID)
      references POPISNA_KOMISIJA (POP_KOM_ID)
go

alter table CLAN_POPISNE_KOMISIJE
   add constraint FK_CLAN_POP_CLAN_POPI_CLAN_KOM foreign key (CLAN_ID)
      references CLAN_KOMISIJE (CLAN_ID)
go

alter table FUNKCIJA
   add constraint FK_FUNKCIJA_FUNKCIJA__POSLOVNI foreign key (POSL_SIS_ID)
      references POSLOVNI_SISTEM (POSL_SIS_ID)
go

alter table GRUPA_ROBA
   add constraint FK_GRUPA_RO_POSLOVNI__POSLOVNI foreign key (POSL_SIS_ID)
      references POSLOVNI_SISTEM (POSL_SIS_ID)
go

alter table MAGACIN
   add constraint FK_MAGACIN_MAGACIN_P_SEKTOR foreign key (SEKT_ID)
      references SEKTOR (SEKT_ID)
go

alter table POPISNI_DOKUMENT
   add constraint FK_POPISNI__GODINA_PO_POSLOVNA foreign key (POSL_GOD_ID)
      references POSLOVNA_GODINA (POSL_GOD_ID)
go

alter table POPISNI_DOKUMENT
   add constraint FK_POPISNI__NAZIV_ODE_MAGACIN foreign key (MAG_ID)
      references MAGACIN (MAG_ID)
go

alter table POPISNI_DOKUMENT
   add constraint FK_POPISNI__POSLOVNI__POSLOVNI foreign key (POSL_SIS_ID)
      references POSLOVNI_SISTEM (POSL_SIS_ID)
go

alter table POPISNI_DOKUMENT
   add constraint FK_POPISNI__PROPISNA__POPISNA_ foreign key (POP_KOM_ID)
      references POPISNA_KOMISIJA (POP_KOM_ID)
go

alter table POSLOVNA_GODINA
   add constraint FK_POSLOVNA_POSLOVNA__POSLOVNI foreign key (POSL_SIS_ID)
      references POSLOVNI_SISTEM (POSL_SIS_ID)
go

alter table POSLOVNI_PARTNER
   add constraint FK_POSLOVNI_PARTNER_POSLOVNI foreign key (POSL_SIS_ID)
      references POSLOVNI_SISTEM (POSL_SIS_ID)
go

alter table POSLOVNI_PARTNER
   add constraint FK_POSLOVNI_PREDUZECE_POSLOVNI foreign key (POS_POSL_SIS_ID)
      references POSLOVNI_SISTEM (POSL_SIS_ID)
go

alter table POSLOVNI_SISTEM
   add constraint FK_POSLOVNI_SEDISTE_MESTO foreign key (MESTO_ID)
      references MESTO (MESTO_ID)
go

alter table PROMETNI_DOKUMENT
   add constraint FK_PROMETNI_GODINA_PR_POSLOVNA foreign key (POSL_GOD_ID)
      references POSLOVNA_GODINA (POSL_GOD_ID)
go

alter table PROMETNI_DOKUMENT
   add constraint FK_PROMETNI_LOKACIJA__MAGACIN foreign key (MAG_ID)
      references MAGACIN (MAG_ID)
go

alter table PROMETNI_DOKUMENT
   add constraint FK_PROMETNI_RELATIONS_MAGACIN foreign key (MAG_MAG_ID)
      references MAGACIN (MAG_ID)
go

alter table PROMETNI_DOKUMENT
   add constraint FK_PROMETNI_STRANKA_U_POSLOVNI foreign key (POSL_SIS_ID)
      references POSLOVNI_SISTEM (POSL_SIS_ID)
go

alter table RADNIK
   add constraint FK_RADNIK_FUNKCIJA__FUNKCIJA foreign key (FUNKCIJA_ID)
      references FUNKCIJA (FUNKCIJA_ID)
go

alter table RASPORED_NA_RADNA_MESTA
   add constraint FK_RASPORED_RASPOREDJ_RADNIK foreign key (RADNIK_ID)
      references RADNIK (RADNIK_ID)
go

alter table RASPORED_NA_RADNA_MESTA
   add constraint FK_RASPORED_RASPORED__MAGACIN foreign key (MAG_ID)
      references MAGACIN (MAG_ID)
go

alter table ROBA
   add constraint FK_ROBA_JEDINICA__JEDINICA foreign key (JEDINICA_ID)
      references JEDINICA_MERE (JEDINICA_ID)
go

alter table ROBA
   add constraint FK_ROBA_ROBA_PRIP_GRUPA_RO foreign key (GRUPA_ID)
      references GRUPA_ROBA (GRUPA_ID)
go

alter table ROBNA_KARTICA
   add constraint FK_ROBNA_KA_GODINA_RO_POSLOVNA foreign key (POSL_GOD_ID)
      references POSLOVNA_GODINA (POSL_GOD_ID)
go

alter table ROBNA_KARTICA
   add constraint FK_ROBNA_KA_RELATIONS_MAGACIN foreign key (MAG_ID)
      references MAGACIN (MAG_ID)
go

alter table ROBNA_KARTICA
   add constraint FK_ROBNA_KA_ROBA_CIJA_ROBA foreign key (ROBA_ID)
      references ROBA (ROBA_ID)
go

alter table SEKTOR
   add constraint FK_SEKTOR_POSLOVNI__POSLOVNI foreign key (POSL_SIS_ID)
      references POSLOVNI_SISTEM (POSL_SIS_ID)
go

alter table STAVKA_POPISNOG_DOKUMENTA
   add constraint FK_STAVKA_P_POPISNI_D_POPISNI_ foreign key (POP_DOK_ID)
      references POPISNI_DOKUMENT (POP_DOK_ID)
go

alter table STAVKA_POPISNOG_DOKUMENTA
   add constraint FK_ST_P_RA_U_ST_ROBA foreign key (ROBA_ID)
      references ROBA (ROBA_ID)
go

alter table STAVKA_PROMETNOG_DOKUMENTA
   add constraint FK_STA_P_U_ST_ROBA foreign key (ROBA_ID)
      references ROBA (ROBA_ID)
go

alter table STAVKA_PROMETNOG_DOKUMENTA
   add constraint FK_STAVKA_P_STAVKA_PR_PROMETNI foreign key (PROM_DOK_ID)
      references PROMETNI_DOKUMENT (PROM_DOK_ID)
go

