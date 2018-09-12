import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './instruction.reducer';
import { IInstruction } from 'app/shared/model/instruction.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IInstructionUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IInstructionUpdateState {
  isNew: boolean;
}

export class InstructionUpdate extends React.Component<IInstructionUpdateProps, IInstructionUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { instructionEntity } = this.props;
      const entity = {
        ...instructionEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
      this.handleClose();
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/instruction');
  };

  render() {
    const { instructionEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterSampleApplication2App.instruction.home.createOrEditLabel">Create or edit a Instruction</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : instructionEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="instruction-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="scheduleItemIdLabel" for="scheduleItemId">
                    Schedule Item Id
                  </Label>
                  <AvField id="instruction-scheduleItemId" type="number" className="form-control" name="scheduleItemId" />
                </AvGroup>
                <AvGroup>
                  <Label id="videoURLLabel" for="videoURL">
                    Video URL
                  </Label>
                  <AvField id="instruction-videoURL" type="text" name="videoURL" />
                </AvGroup>
                <AvGroup>
                  <Label id="photoURLsLabel" for="photoURLs">
                    Photo UR Ls
                  </Label>
                  <AvField id="instruction-photoURLs" type="text" name="photoURLs" />
                </AvGroup>
                <AvGroup>
                  <Label id="instructionLabel" for="instruction">
                    Instruction
                  </Label>
                  <AvField id="instruction-instruction" type="text" name="instruction" />
                </AvGroup>
                <AvGroup>
                  <Label id="titleLabel" for="title">
                    Title
                  </Label>
                  <AvField id="instruction-title" type="text" name="title" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/instruction" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />&nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />&nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  instructionEntity: storeState.instruction.entity,
  loading: storeState.instruction.loading,
  updating: storeState.instruction.updating
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(InstructionUpdate);
