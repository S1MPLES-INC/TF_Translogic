package pe.edu.upc.translogic.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

import pe.edu.upc.translogic.entities.Administrator;
import pe.edu.upc.translogic.entities.Group;
import pe.edu.upc.translogic.entities.Driver;
import pe.edu.upc.translogic.entities.Travel;
import pe.edu.upc.translogic.entities.Route;
import pe.edu.upc.translogic.entities.Tramo;
import pe.edu.upc.translogic.repositories.AdministratorRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AdministratorController {
    @Autowired
    private AdministratorRepository administratorRepository;

    // Administrators - All Information (NO SE USA)
    @GetMapping("/admins")
    public ResponseEntity<List<Administrator>> getAllAdmins() {
        List<Administrator> administrators = administratorRepository.findAll();

        if (administrators.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (Administrator item : administrators) {
            for (Group group : item.getGroups()) {
                group.setAdministrator(null);
                group.setDrivers(null);
            }
            for (Driver driver : item.getDrivers()) {
                driver.setAdministrator(null);
                driver.setGroup(null);
                driver.setTravels(null);
            }
            for (Travel travel : item.getTravels()) {
                travel.setAdministrator(null);
                travel.setDriver(null);
                travel.setRoute(null);
            }
            for (Route route : item.getRoutes()) {
                route.setAdministrator(null);
                route.setTravels(null);

                for (Tramo tramo : route.getTramos()) {
                    tramo.setRoute(null);
                }
            }
        }

        return new ResponseEntity<List<Administrator>>(administrators, HttpStatus.OK);
    }

    // Administrators - Individual Information
    @GetMapping("/admins/info")
    public ResponseEntity<List<Administrator>> getAllAdminsInfo() {
        List<Administrator> administrators = administratorRepository.findAll();

        if (administrators.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (Administrator a : administrators) {
            a.setGroups(null);
            a.setDrivers(null);
            a.setTravels(null);
            a.setRoutes(null);
        }
        return new ResponseEntity<List<Administrator>>(administrators, HttpStatus.OK);
    }

    // Administrators -> Group by ID
    @GetMapping("/admins/groups/{id}")
    public ResponseEntity<List<Group>> getGroupOfAdminById(@PathVariable("id") Long id) {

        Administrator administrator = administratorRepository.findById(id).get();
        List<Group> groups = administrator.getGroups();
        // List<Group> groups = administratorRepository.findGroupsById(id); // NO FUNCA

        if (groups.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (Group item : groups) {
            item.setAdministrator(null);
            item.setDrivers(null);
        }

        return new ResponseEntity<List<Group>>(groups, HttpStatus.OK);
    }

    // Administrators -> Driver by ID
    @GetMapping("/admins/drivers/{id}")
    public ResponseEntity<List<Driver>> getDriverOfAdminById(@PathVariable("id") Long id) {

        Administrator administrator = administratorRepository.findById(id).get();
        List<Driver> drivers = administrator.getDrivers();

        if (drivers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (Driver item : drivers) {
            item.setAdministrator(null);
            item.setGroup(null);
            item.setTravels(null);
        }

        return new ResponseEntity<List<Driver>>(drivers, HttpStatus.OK);
    }

    // Administrators -> Travel by ID
    @GetMapping("/admins/travels/{id}")
    public ResponseEntity<List<Travel>> getTravelOfAdminByIp(@PathVariable("id") Long id) {

        Administrator administrator = administratorRepository.findById(id).get();
        List<Travel> travels = administrator.getTravels();

        if (travels.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (Travel item : travels) {
            item.setAdministrator(null);
            item.setDriver(null);
            item.setRoute(null);
            item.setVehicle(null);
        }

        return new ResponseEntity<List<Travel>>(travels, HttpStatus.OK);
    }

    // Administrators -> Route by ID
    @GetMapping("/admins/routes/{id}")
    public ResponseEntity<List<Route>> getRouteOfAdminByIp(@PathVariable("id") Long id) {

        Administrator administrator = administratorRepository.findById(id).get();
        List<Route> routes = administrator.getRoutes();

        if (routes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (Route item : routes) {
            item.setAdministrator(null);
            item.setTravels(null);
            item.setTramos(null);
        }

        return new ResponseEntity<List<Route>>(routes, HttpStatus.OK);
    }

    // Administrator (All Information): Find -> Id
    @GetMapping("/admins/id/{id}")
    public ResponseEntity<Administrator> getAdminById(@PathVariable("id") Long id) {
        Administrator foundAdmin = administratorRepository.findById(id).get();

        for (Group group : foundAdmin.getGroups()) {
            group.setAdministrator(null);
            group.setDrivers(null);
        }
        for (Driver driver : foundAdmin.getDrivers()) {
            driver.setAdministrator(null);
            driver.setGroup(null);
            driver.setTravels(null);
        }
        for (Travel travel : foundAdmin.getTravels()) {
            travel.setAdministrator(null);
            travel.setDriver(null);
            travel.setRoute(null);
        }
        for (Route route : foundAdmin.getRoutes()) {
            route.setAdministrator(null);
            route.setTravels(null);

            for (Tramo tramo : route.getTramos()) {
                tramo.setRoute(null);
            }
        }

        return new ResponseEntity<Administrator>(foundAdmin, HttpStatus.OK);
    }

    // Administrator (All Information): Find -> Email
    @GetMapping("/admins/email/{email}")
    public ResponseEntity<Administrator> getAdminByEmail(@PathVariable("email") String email) {
        Administrator foundAdmin = administratorRepository.findByEmail(email);

        for (Group group : foundAdmin.getGroups()) {
            group.setAdministrator(null);
            group.setDrivers(null);
        }
        for (Driver driver : foundAdmin.getDrivers()) {
            driver.setAdministrator(null);
            driver.setGroup(null);
            driver.setTravels(null);
        }
        for (Travel travel : foundAdmin.getTravels()) {
            travel.setAdministrator(null);
            travel.setDriver(null);
            travel.setRoute(null);
        }
        for (Route route : foundAdmin.getRoutes()) {
            route.setAdministrator(null);
            route.setTravels(null);

            for (Tramo tramo : route.getTramos()) {
                tramo.setRoute(null);
            }
        }

        return new ResponseEntity<Administrator>(foundAdmin, HttpStatus.OK);
    }

    // Administrator (Individual Information): Find -> Id
    @GetMapping("/admins/info/id/{id}")
    public ResponseEntity<Administrator> getAdminInfoById(@PathVariable("id") Long id) {
        Administrator foundAdmin = administratorRepository.findById(id).get();

        foundAdmin.setGroups(null);
        foundAdmin.setDrivers(null);
        foundAdmin.setTravels(null);
        foundAdmin.setRoutes(null);

        return new ResponseEntity<Administrator>(foundAdmin, HttpStatus.OK);
    }

    // Administrator (Individual Information): Find -> Email
    @GetMapping("/admins/info/email/{email}")
    public ResponseEntity<Administrator> getAdminInfoByEmail(@PathVariable("email") String email) {
        Administrator foundAdmin = administratorRepository.findByEmail(email);

        foundAdmin.setGroups(null);
        foundAdmin.setDrivers(null);
        foundAdmin.setTravels(null);
        foundAdmin.setRoutes(null);

        return new ResponseEntity<Administrator>(foundAdmin, HttpStatus.OK);
    }

    // Administrator: Add
    @PostMapping("/admins")
    public ResponseEntity<Administrator> addAdministrator(@RequestBody Administrator adminBody) {
        Administrator foundAdmin = administratorRepository.save(new Administrator(adminBody.getNames(),
                adminBody.getSurnames(), adminBody.getEmail(), adminBody.getPhone(), adminBody.getNickname(),
                adminBody.getPassword()));

        foundAdmin.setGroups(null);
        foundAdmin.setDrivers(null);
        foundAdmin.setTravels(null);
        foundAdmin.setRoutes(null);

        return new ResponseEntity<Administrator>(foundAdmin, HttpStatus.CREATED);
    }

    // Administrator: Edit -> Id
    @PutMapping("/admins/id/{id}")
    public ResponseEntity<Administrator> updateAdminById(@PathVariable("id") Long id,
            @RequestBody Administrator bodyAdmin) {
        Administrator foundAdmin = administratorRepository.findById(id).get();

        if (bodyAdmin.getNames() != null)
            foundAdmin.setNames(bodyAdmin.getNames());
        if (bodyAdmin.getSurnames() != null)
            foundAdmin.setSurnames(bodyAdmin.getSurnames());
        if (bodyAdmin.getEmail() != null)
            foundAdmin.setEmail(bodyAdmin.getEmail());
        if (bodyAdmin.getPhone() != null)
            foundAdmin.setPhone(bodyAdmin.getPhone());
        if (bodyAdmin.getNickname() != null)
            foundAdmin.setNickname(bodyAdmin.getNickname());
        if (bodyAdmin.getPassword() != null)
            foundAdmin.setPassword(bodyAdmin.getPassword());

        if (bodyAdmin.getGroups() != null)
            foundAdmin.setGroups(bodyAdmin.getGroups());
        if (bodyAdmin.getDrivers() != null)
            foundAdmin.setDrivers(bodyAdmin.getDrivers());
        if (bodyAdmin.getTravels() != null)
            foundAdmin.setTravels(bodyAdmin.getTravels());
        if (bodyAdmin.getRoutes() != null)
            foundAdmin.setRoutes(bodyAdmin.getRoutes());

        administratorRepository.save(foundAdmin);

        return new ResponseEntity<Administrator>(foundAdmin, HttpStatus.OK);
    }

    // Administrator: Delete -> Id
    @DeleteMapping("/admins/id/{id}")
    public ResponseEntity<List<Administrator>> deleteAdminById(@PathVariable("id") Long id) {
        administratorRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}