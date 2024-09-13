package au.edu.rmit.sept.webapp.repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import au.edu.rmit.sept.webapp.dto.AppointmentDTO;
import org.springframework.jdbc.datasource.init.UncategorizedScriptException;
import org.springframework.stereotype.Repository;

@Repository
public class AppointmentRepositoryImpl implements AppointmentRepository {
    private final DataSource source;

    public AppointmentRepositoryImpl(DataSource source) {
        this.source = source;
    }

    @Override
    public List<AppointmentDTO> findAll() {
        try {
            String sql = "SELECT ap.\"APPOINTMENTID\", ap.\"APPOINTMENTDATE\", ap.\"APPOINTMENTTIME\", p.\"NAME\", u.\"NAME\", "
                    + "ap.\"GENERALNOTES\", ap.\"STATUS\" "
                    + "FROM \"APPOINTMENT\" ap "
                    + "INNER JOIN \"PET\" p ON ap.\"PETID\" = p.\"PETID\" "
                    + "INNER JOIN \"USER\" u ON ap.\"USERID\" = u.\"USERID\"";
            Connection connection = this.source.getConnection();
            PreparedStatement stm = connection.prepareStatement(sql);
            List<AppointmentDTO> appointments = new ArrayList<>();
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                AppointmentDTO m = new AppointmentDTO(rs.getLong(1), rs.getDate(2).toLocalDate(), rs.getTime(3).toLocalTime(), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7) );
                appointments.add(m);
            }
            connection.close();
            return appointments;
        } catch (SQLException e) {
            throw new UncategorizedScriptException("Error in findAll", e);
        }
    }

    @Override
    public AppointmentDTO findById(Long id) {
        try {
            Connection connection = this.source.getConnection();
            String sql = "SELECT ap.\"APPOINTMENTID\", ap.\"APPOINTMENTDATE\", ap.\"APPOINTMENTTIME\", p.\"NAME\" AS \"PETNAME\", u.\"NAME\" AS \"USERNAME\", "
                    + "ap.\"GENERALNOTES\", ap.\"STATUS\" "
                    + "FROM \"APPOINTMENT\" ap "
                    + "INNER JOIN \"PET\" p ON ap.\"PETID\" = p.\"PETID\" "
                    + "INNER JOIN \"USER\" u ON ap.\"USERID\" = u.\"USERID\" "
                    + "WHERE ap.\"APPOINTMENTID\" = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setLong(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                AppointmentDTO appointment = new AppointmentDTO(rs.getLong(1), rs.getDate(2).toLocalDate(), rs.getTime(3).toLocalTime(), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
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
    public void save(AppointmentDTO appointmentDTO) {
        try (Connection connection = this.source.getConnection()) {
            String sql = "UPDATE \"APPOINTMENT\"\n" +
                    "SET \n" +
                    "    \"APPOINTMENTDATE\" = ?, \n" +
                    "    \"APPOINTMENTTIME\" = ?, " +
                    "    \"GENERALNOTES\" = ?, \n" +
                    "    \"STATUS\" = ?\n" +
                    "WHERE \n" +
                    "    \"APPOINTMENTID\" = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDate(1, Date.valueOf(appointmentDTO.AppointmentDate()));
            stm.setTime(2, Time.valueOf(appointmentDTO.AppointmentTime()));
            stm.setString(3, appointmentDTO.GeneralNotes());
            stm.setString(4, appointmentDTO.Status());
            stm.setLong(5, appointmentDTO.AppointmentID());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new UncategorizedScriptException("Error in save", e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = this.source.getConnection()) {
            String sql = "DELETE FROM \"APPOINTMENT\" WHERE \"APPOINTMENTID\" = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setLong(1, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new UncategorizedScriptException("Error in delete", e);
        }
    }
}
