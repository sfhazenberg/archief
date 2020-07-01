using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Floor3Trigger : MonoBehaviour {

    public ElevWallE ElevWallE;
    public Floor3Door Floor3Door;

    private void OnCollisionEnter(Collision collision) {
        if (collision.gameObject.CompareTag("Player")) {
            print("Floor3Trigger active");
            Floor3Door.LowerDoor();
            ElevWallE.LowerDoor();
        }
    }

    private void OnCollisionExit(Collision collision) {
        if (collision.gameObject.CompareTag("Player")) {
            Floor3Door.RaiseDoor();
            ElevWallE.RaiseDoor();
        }
    }
}
