package au.edu.rmit.sept.webapp.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.init.UncategorizedScriptException;
import org.springframework.stereotype.Repository;

import au.edu.rmit.sept.webapp.models.Appointment;

@Repository
public class AppointmentRepositoryImpl implements AppointmentRepository {
    private final DataSource source;

    public AppointmentRepositoryImpl(DataSource source) {
        this.source = source;
    }

    @Override
    public List<Appointment> findAll() {
        try {
            Connection connection = this.source.getConnection();
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM appointments order by id;");
            List<Appointment> appointments = new ArrayList<>();
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Appointment m = new Appointment(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7) );
                appointments.add(m);
            }
            connection.close();
            return appointments;
        } catch (SQLException e) {
            throw new UncategorizedScriptException("Error in findAll", e);
        }
    }

    @Override
    public Appointment findById(Long id) {
        try {
            Connection connection = this.source.getConnection();
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM appointments WHERE id = ?;");
            stm.setLong(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Appointment appointment = new Appointment(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                connection.close();
                return appointment;
            } else {
                connection.close();
                return null;
            }
        } catch (SQLException e) {
            throw new UncategorizedScriptException("Error in findById", e);
        }
    }

    @Override
    public void save(Appointment appointment) {
        try (Connection connection = this.source.getConnection()) {
            PreparedStatement stm = connection.prepareStatement("UPDATE appointments SET date = ?, time = ?, pet_name = ?, veterinarian_name = ?, notes = ?, status = ? WHERE id = ?;");
            stm.setString(1, appointment.date());
            stm.setString(2, appointment.time());
            stm.setString(3, appointment.petName());
            stm.setString(4, appointment.veterinarianName());
            stm.setString(5, appointment.notes());
            stm.setString(6, appointment.status());
            stm.setLong(7, appointment.id());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new UncategorizedScriptException("Error in save", e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = this.source.getConnection()) {
            PreparedStatement stm = connection.prepareStatement("DELETE FROM appointments WHERE id = ?;");
            stm.setLong(1, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new UncategorizedScriptException("Error in delete", e);
        }
    }
}
