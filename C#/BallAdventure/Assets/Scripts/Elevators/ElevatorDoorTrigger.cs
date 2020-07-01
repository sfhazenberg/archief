using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ElevatorDoorTrigger : MonoBehaviour {

    public Floor1Door Floor1Door;
    public ElevWallN elevWallN;
    //public Elevator Elevator;

    private Collider boxCollider;

    private void Start() {
        boxCollider = GetComponent<BoxCollider>();
    }

    private void OnCollisionEnter(Collision collision) {
        if (collision.gameObject.CompareTag("Player")) {
            Floor1Door.LowerDoor();
            elevWallN.LowerDoor();
            //Elevator.GetFloor();
        }
    }

    private void OnCollisionExit(Collision collision) {
        Floor1Door.RaiseDoor();
        elevWallN.RaiseDoor();
    }

    public void DisableCollider() {
        boxCollider.enabled = false;
    }

    public void EnableCollider() {
        boxCollider.enabled = true;
    }

}
