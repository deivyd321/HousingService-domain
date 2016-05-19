package Testing.Package;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.deivydas.voroneckis.domain.Student;
import com.deivydas.voroneckis.domain.NegativeOrNullNumberException;
import com.deivydas.voroneckis.domain.Room;
import com.deivydas.voroneckis.service.HousingFacade;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author vdeiv
 */
public class HousingFacadeTest {

    private Room room;
    private Student student;
    private HousingFacade housingFacade;

    @Before
    public void setUp() {
        System.out.println("The tests are starting");
        housingFacade = new HousingFacade("FAKE");
        room = new Room();
        housingFacade.addRoom(room);
        student = new Student();
        housingFacade.addStudent(student);
    }

    @Test
    public void addRoom_RoomAddedCorrectly(){
        assertTrue("The are not any rooms saved!", housingFacade.getRoomsList().size()>0);
    }
    
    @Test
    public void updateRoom_RoomUpdatedCorrectly() throws NegativeOrNullNumberException{
        room = housingFacade.getRoomsList().get(0);
        room.setNumberOfMaxStudents(3);
        housingFacade.updateRoom(room);
        assertEquals(3, room.getMaxStudents());
    }
    
    @Test
    public void updateStudent_StudentUpdatedCorrectly(){
        student = housingFacade.getStudentsList().get(0);
        student.setName("newName");
        housingFacade.updateStudent(student);
        assertEquals("newName", student.getName());
    }
    
    @Test(expected = NegativeOrNullNumberException.class)
    public void setNumberOfMaxStudents_ThrowsAnException() throws NegativeOrNullNumberException{
        room = housingFacade.getRoomsList().get(0);
        room.setNumberOfMaxStudents(0);
    }
    
    @Test
    public void addStudent_StudentAddedCorrectly(){
        assertTrue("The are not any students saved!", housingFacade.getStudentsList().size()>0);
    }
    
    @Test
    public void addStudentToRoom_StudentAddedToRoomCorrectly() {
        housingFacade.addStudentToRoom(room.getId(), student.getId());
        assertEquals(false, room.isFree());
    }
    
    @Test
    public void removeRoom_RemovedRoomCorrectly(){
        housingFacade.removeRoom(room);
        assertEquals(2, housingFacade.getRoomsList().size()); 
    }
    
    @Test
    public void removeStudent_RemovedStudentCorrectly(){
        housingFacade.removeStudent(student);
        assertEquals(0, housingFacade.getStudentsList().size()); 
    }

}
