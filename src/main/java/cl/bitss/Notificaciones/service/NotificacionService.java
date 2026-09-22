package cl.bitss.Notificaciones.service;
import cl.bitss.Notificaciones.model.Notificacion;
import cl.bitss.Notificaciones.repository.NotificacionRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class NotificacionService {
    private final NotificacionRepository repository;
    public NotificacionService(NotificacionRepository repository) {
        this.repository = repository;
    }

    public List<Notificacion> obtenerTodos() {
        return repository.findAll();
    }

    public Optional<Notificacion> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Notificacion crear(Notificacion notificacion) {
        return repository.save(notificacion);
    }

    public Optional<Notificacion> actualizar(Long id, Notificacion datos) {
        Optional<Notificacion> encontrada = repository.findById(id);

        if (encontrada.isPresent()) {
            Notificacion notificacion = encontrada.get();
            notificacion.setUsuarioId(datos.getUsuarioId());
            notificacion.setTipo(datos.getTipo());
            notificacion.setMensaje(datos.getMensaje());
            notificacion.setEstado(datos.getEstado());
            return Optional.of(repository.save(notificacion));
        }
        return Optional.empty();
    }

    public boolean eliminar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Notificacion> obtenerPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    public List<Notificacion> obtenerPorTipo(String tipo) {
        return repository.findByTipo(tipo);
    }
}