// 代码生成时间: 2025-10-14 16:51:55
import java.io.File;
import java.io.IOException;
import java.nio.file.ClosedWatchServiceException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

/**
 * FileWatcherService provides functionality to monitor file changes and notify
 * when changes occur in the specified directory. It uses Apache Commons IO
 * library for file monitoring.
 */
public class FileWatcherService {

    private final FileAlterationMonitor monitor;
    private final FileAlterationObserver observer;
    private final WatchService watchService;
    private final List<WatchKey> keys;
    private volatile boolean running;

    public FileWatcherService(String directoryPath, long interval) throws IOException {
        this.watchService = FileSystems.getDefault().newWatchService();
        this.keys = new ArrayList<>();
        this.running = true;

        File directory = new File(directoryPath);
        if (!directory.exists()) {
            throw new IllegalArgumentException("Directory does not exist: " + directoryPath);
        }

        observer = new FileAlterationObserver(Paths.get(directoryPath));
        monitor = new FileAlterationMonitor(interval, observer);
        monitor.start();
    }

    /**
     * Starts the file watch service.
     */
    public void startService() {
        new Thread(() -> {
            try {
                for (WatchKey key; (key = watchService.poll()) != null; ) {
                    for (WatchEvent<?> event : key.pollEvents()) {
                        WatchEvent.Kind<?> kind = event.kind();
                        if (kind == StandardWatchEventKinds.ENTRY_CREATE ||
                                kind == StandardWatchEventKinds.ENTRY_DELETE ||
                                kind == StandardWatchEventKinds.ENTRY_MODIFY) {

                            WatchEvent<Path> ev = (WatchEvent<Path>) event;
                            Path filename = ev.context();
                            notifyFileChanged(filename, kind);
                        }
                    }
                    boolean valid = key.reset();
                    if (!valid) {
                        break;
                    }
                }
            } catch (InterruptedException | ClosedWatchServiceException e) {
                Thread.currentThread().interrupt();
                System.out.println("File watching service interrupted or closed: " + e.getMessage());
            } finally {
                stopService();
            }
        }).start();
    }

    /**
     * Stops the file watch service.
     */
    public void stopService() {
        running = false;
        try {
            monitor.stop();
            watchService.close();
        } catch (IOException e) {
            System.out.println("Error stopping file watching service: " + e.getMessage());
        }
    }

    /**
     * Notifies when a file change occurs.
     * @param filename The name of the file that changed.
     * @param kind The type of change that occurred.
     */
    private void notifyFileChanged(Path filename, WatchEvent.Kind<?> kind) {
        System.out.printf("File: %s, changed type: %s%n", filename, kind);
    }

    /**
     * Main method to test the FileWatcherService.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        try {
            FileWatcherService service = new FileWatcherService("./watched_directory", 10000);
            service.startService();
        } catch (IOException e) {
            System.out.println("Error starting file watching service: " + e.getMessage());
        }
    }
}
