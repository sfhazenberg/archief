using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Elevator : MonoBehaviour
{
    public Floor2Door Floor2Door;
    public Floor3Door Floor3Door;
    public ElevWallW ElevWallW;
    public ElevWallE ElevWallE;
    public Transform floor1;
    public Transform floor2;
    public Transform floor3;
    public GameObject lvl1Button;
    public GameObject lvl2Button;
    public GameObject lvl3Button;
    public GameObject lvl2Trigger;
    public GameObject lvl3Trigger;
    public float speed;

    private bool PlayerDeparted;    //checken of de speler de lift verlaat en dan de boel resetten. Oid. Tom Poes, verzin een list
    private Vector3 currentFloor;
    private Vector3 targetFloor;
    private Vector3 f1;
    private Vector3 f2;
    private Vector3 f3;

    private const int FLOORCOUNT = 3;
    private List<Vector3> floors = new List<Vector3>();
    //private Vector3[] floors = new Vector3[FLOORCOUNT];

    private bool closingDoors;

    void Start() {

        floors.Add(floor1.position);
        floors.Add(floor2.position);
        floors.Add(floor3.position);

        f1 = floor1.position;
        f2 = floor2.position;
        f3 = floor3.position;
    }

    void Update() {
        float step = speed * Time.deltaTime;

        if (currentFloor != targetFloor) {
            transform.position = Vector3.MoveTowards(transform.position, targetFloor, step);
        }

        if (transform.position == f2) {
            lvl2Trigger.SetActive(true);
            Debug.Log("Currently on floor 2");
        }
        else {
            lvl2Trigger.SetActive(false);
        }

        if (transform.position == f3) {
            lvl3Trigger.SetActive(true);
            Debug.Log("Currently on floor 3");
        }
        else {
            lvl3Trigger.SetActive(false);
        }

        /*else {
            CheckButtons(); //idee is om nietgebruikte knoppen tijdens het omhoog/omlaag gaan inactief worden. Tevens: als lift = f2, dan lvl2button.setActive(false), dat idee.
        }*/
        //CheckButtons();
    }

    /*private void SetCurrentFloor() {
        currentFloor = transform.position;
        Debug.Log("New currentfloor set");
    }*/

    /*void CloseDoors() {     //meant to close all doors when elevator is in motion. Currently not working as intended.
        Floor2Door.RaiseDoor();
        Floor3Door.RaiseDoor();
        ElevWallE.RaiseDoor();
        ElevWallW.RaiseDoor();
    }*/

    private void CheckButtons() {
        if (!lvl1Button.activeSelf) {
            //lvl1Button.SetActive(true);
            Debug.Log("lvl1button niet actief, Henk");
        }
    }

    public void ToFloor(int floorNr) {      //sends elevator to chosen floor
        if (floorNr >= 0 && floorNr <= 2)
            targetFloor = floors[floorNr];
    }
}
