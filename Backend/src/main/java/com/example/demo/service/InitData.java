package com.example.demo.service;

import com.example.demo.dao.*;
import com.example.demo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Service
public class InitData {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private FieldDao fieldDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SeasonDao seasonDao;

    @Autowired
    private TypeCropDao typeCropDao;

    private User user;

    @PostConstruct
    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);

        user = new User();
        user.setId(1L);
        user.setName("test");
        user.setPassword(passwordEncoder.encode("test"));
        user.setFirstName("Test");
        user.setUserLastName("Test");
        user.setEmail("test@o2.pl");
        user.setTelephonNumber("123456789");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRole(userRoles);
        userDao.save(user);
        initFieldUser();
        initTypeCrop();
    }


    public void initFieldUser() {
        Field field = new Field();
        field.setFieldId(1L);
        field.setName("Pole");
        field.setActive(true);
        field.setArea(new BigDecimal("20.24"));
        field.setProperty("Dzierżawa");
        field.setNumberRegistration("202");
        field.setArgonomicCategory("Lekka");
        field.setWojewodztwo("Lubelskie");
        field.setGmina("Strzyżewice");
        field.setMiejscowosc("Bystrzyca nowa");
        field.setNumber("060912_2.0013");
        field.setUser(user);
        fieldDao.save(field);
        field.setFieldId(2L);
        field.setName("Pole2");
        field.setNumberRegistration("221/1");
        fieldDao.save(field);
        field.setName("Pole3");
        field.setFieldId(3L);
        fieldDao.save(field);
    }

    public void initTypeCrop() {
        typeCropDao.save(new TypeCrop("Zdecyduję później"));
        typeCropDao.save(new TypeCrop("Agrest"));
        typeCropDao.save(new TypeCrop("Aksamitka"));
        typeCropDao.save(new TypeCrop("Arbuz"));
        typeCropDao.save(new TypeCrop("Aster"));
        typeCropDao.save(new TypeCrop("Aster chiński"));
        typeCropDao.save(new TypeCrop("Bakłażan"));
        typeCropDao.save(new TypeCrop("Batat"));
        typeCropDao.save(new TypeCrop("Biała porzeczka"));
        typeCropDao.save(new TypeCrop("Bobik"));
        typeCropDao.save(new TypeCrop("Borówka"));
        typeCropDao.save(new TypeCrop("Brokuł"));
        typeCropDao.save(new TypeCrop("Brukiew"));
        typeCropDao.save(new TypeCrop("Burak cukrowy"));
        typeCropDao.save(new TypeCrop("Burak pastewny"));
        typeCropDao.save(new TypeCrop("Burak ćwikłowy"));
        typeCropDao.save(new TypeCrop("Bób"));
        typeCropDao.save(new TypeCrop("Cebula biała"));
        typeCropDao.save(new TypeCrop("Cebula czerwona"));
        typeCropDao.save(new TypeCrop("Cebula ozima"));
        typeCropDao.save(new TypeCrop("Chmiel zwyczajny"));
        typeCropDao.save(new TypeCrop("Chrzan"));
        typeCropDao.save(new TypeCrop("Cukinie"));
        typeCropDao.save(new TypeCrop("Cykoria"));
        typeCropDao.save(new TypeCrop("Czarna porzeczka"));
        typeCropDao.save(new TypeCrop("Czereśnia"));
        typeCropDao.save(new TypeCrop("Czerwona porzeczka"));
        typeCropDao.save(new TypeCrop("Czosnek"));
        typeCropDao.save(new TypeCrop("Dynie"));
        typeCropDao.save(new TypeCrop("Dzika marchew"));
        typeCropDao.save(new TypeCrop("Dzika róża"));
        typeCropDao.save(new TypeCrop("Fasola"));
        typeCropDao.save(new TypeCrop("Gorczyca biała"));
        typeCropDao.save(new TypeCrop("Gorczyca sarepska"));
        typeCropDao.save(new TypeCrop("Groch"));
        typeCropDao.save(new TypeCrop("Gruszka europejska"));
        typeCropDao.save(new TypeCrop("Gryka"));
        typeCropDao.save(new TypeCrop("Inne"));
        typeCropDao.save(new TypeCrop("Jabłoń"));
        typeCropDao.save(new TypeCrop("Jagoda kamczacka"));
        typeCropDao.save(new TypeCrop("Jarmuż"));
        typeCropDao.save(new TypeCrop("Jęczmień jary browarny"));
        typeCropDao.save(new TypeCrop("Jęczmień jary konsumpcyjny"));
        typeCropDao.save(new TypeCrop("Jęczmień jary pastewny"));
        typeCropDao.save(new TypeCrop("Jęczmień ozimy"));
        typeCropDao.save(new TypeCrop("Kalafior"));
        typeCropDao.save(new TypeCrop("Kalarepa"));
        typeCropDao.save(new TypeCrop("Kapusta pekińska"));
        typeCropDao.save(new TypeCrop("Kapusta biała"));
        typeCropDao.save(new TypeCrop("Kapusta brukselka"));
        typeCropDao.save(new TypeCrop("Kapusta czerwona"));
        typeCropDao.save(new TypeCrop("Kapusta pastewna"));
        typeCropDao.save(new TypeCrop("Kapusta włoska"));
        typeCropDao.save(new TypeCrop("Karczoch"));
        typeCropDao.save(new TypeCrop("Kminek"));
        typeCropDao.save(new TypeCrop("Kolendra"));
        typeCropDao.save(new TypeCrop("Koniczyna biała"));
        typeCropDao.save(new TypeCrop("Koniczyna białoróżowa"));
        typeCropDao.save(new TypeCrop("Koniczyna czerwona"));
        typeCropDao.save(new TypeCrop("Konopie"));
        typeCropDao.save(new TypeCrop("Koper"));
        typeCropDao.save(new TypeCrop("Kukurydza na ziarno"));
        typeCropDao.save(new TypeCrop("Kukurydza na kiszonkę"));
        typeCropDao.save(new TypeCrop("Len"));
        typeCropDao.save(new TypeCrop("Leszczyna"));
        typeCropDao.save(new TypeCrop("Mak lekarski"));
        typeCropDao.save(new TypeCrop("Maliny"));
        typeCropDao.save(new TypeCrop("Marchew"));
        typeCropDao.save(new TypeCrop("Mieszanka zbóż"));
        typeCropDao.save(new TypeCrop("Nagietek lekarski"));
        typeCropDao.save(new TypeCrop("Nieużytek"));
        typeCropDao.save(new TypeCrop("Ogórek gruntowy"));
        typeCropDao.save(new TypeCrop("Orzech włoski"));
        typeCropDao.save(new TypeCrop("Owies zielonka"));
        typeCropDao.save(new TypeCrop("Owies jary"));
        typeCropDao.save(new TypeCrop("Owies ozimy"));
        typeCropDao.save(new TypeCrop("Papryka"));
        typeCropDao.save(new TypeCrop("Pietruszka"));
        typeCropDao.save(new TypeCrop("Pomidor gruntowy"));
        typeCropDao.save(new TypeCrop("Por"));
        typeCropDao.save(new TypeCrop("Poziomki"));
        typeCropDao.save(new TypeCrop("Proso"));
        typeCropDao.save(new TypeCrop("Pszenica jara"));
        typeCropDao.save(new TypeCrop("Pszenica ozima"));
        typeCropDao.save(new TypeCrop("Pszenżyto jare"));
        typeCropDao.save(new TypeCrop("Pszenżyto ozime"));
        typeCropDao.save(new TypeCrop("Rabarbar"));
        typeCropDao.save(new TypeCrop("Rokitnik"));
        typeCropDao.save(new TypeCrop("Rukola"));
        typeCropDao.save(new TypeCrop("Rumianek pospolity"));
        typeCropDao.save(new TypeCrop("Rzepa"));
        typeCropDao.save(new TypeCrop("Rzepak jary"));
        typeCropDao.save(new TypeCrop("Rzepak ozimy"));
        typeCropDao.save(new TypeCrop("Rzepik ozimy"));
        typeCropDao.save(new TypeCrop("Rzodkiewka"));
        typeCropDao.save(new TypeCrop("Sałata"));
        typeCropDao.save(new TypeCrop("Seler"));
        typeCropDao.save(new TypeCrop("Soczewica"));
        typeCropDao.save(new TypeCrop("Soja"));
        typeCropDao.save(new TypeCrop("Szczaw"));
        typeCropDao.save(new TypeCrop("Szpinak"));
        typeCropDao.save(new TypeCrop("Słonecznik"));
        typeCropDao.save(new TypeCrop("Trawa na kiszonkęk"));
        typeCropDao.save(new TypeCrop("Trawa na nasiona"));
        typeCropDao.save(new TypeCrop("Truskawka"));
        typeCropDao.save(new TypeCrop("Tymianek właściwy"));
        typeCropDao.save(new TypeCrop("Tytoń"));
        typeCropDao.save(new TypeCrop("Winorośl"));
        typeCropDao.save(new TypeCrop("Wiśnia pospolita"));
        typeCropDao.save(new TypeCrop("Ziemniaki"));
        typeCropDao.save(new TypeCrop("Łubin"));
        typeCropDao.save(new TypeCrop("Łąka"));
        typeCropDao.save(new TypeCrop("Żyto jare"));
        typeCropDao.save(new TypeCrop("Żyto ozime"));
    }

}
